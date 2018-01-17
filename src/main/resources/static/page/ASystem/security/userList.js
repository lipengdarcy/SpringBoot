$(function($) {
	var grid_selector = "#grid_table";
	var pager_selector = "#grid_pager";

	$(grid_selector).jqGrid(
			{
				url : home_url + '/security/user/listData',
				// 编辑url
				editurl : home_url + '/security/user/update',
				datatype : "json",
				width : 'auto',
				autowidth : true,// 宽度自适应
				height : 'auto',// 高度自适应
				autoheight : true,// 高度自适应
				colNames : [ '是否管理员', '员工', '姓名', '账号', '是否可用', '密码' ],
				colModel : [ {
					name : 'isAdmin',
					hidden : true,
				}, {
					name : 'staff.id',
					hidden : true,
				}, {
					name : 'fullname',
					formatter : function(cellvalue, options, rowObject) {
						if (cellvalue)
							return cellvalue;
						return "";
					}
				}, {
					name : 'username'
				}, {
					name : 'isvalid',
					formatter : function(cellvalue, options, rowObject) {
						if (cellvalue == '1')
							return '是';
						return '否';
					}
				}, {
					name : 'plainpassword',
					hidden : true
				} ],
				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				pager : pager_selector,
				rownumbers : true,
				multiselect : true,
				onSelectRow : function(id)// 选择某行时触发事件
				{
					var curRowData = $(grid_selector).jqGrid('getRowData', id);
					if (curRowData.isAdmin == 'true') {
						$(grid_selector).jqGrid("setSelection", id, false);
						$.jGrowl("该账号是注册管理员，不能编辑， 不能删除!");
					}

				},
				onSelectAll : function(rowid, status) { // 点击全选时触发事件
					var rowIds = $(grid_selector).jqGrid('getDataIDs'); // 获取jqgrid中所有数据行的id
					for (var k = 0; k < rowIds.length; k++) {
						var curRowData = $(grid_selector).jqGrid('getRowData',
								rowIds[k]);// 获取指定id所在行的所有数据.
						if (curRowData.isAdmin == 'true') {
							$(grid_selector).jqGrid("setSelection", rowIds[k], false);
						}
					}
				},
				gridComplete: function(){//加载完毕后获取所有的checkbox遍历
					var rowIds = $(grid_selector).jqGrid('getDataIDs'); 
					for (var k = 0; k < rowIds.length; k++) {
						var curRowData = $(grid_selector).jqGrid('getRowData',
								rowIds[k]);
						if (curRowData.isAdmin == 'true') {
							$('#'+rowIds[k]).find("input[type='checkbox']").attr('disabled', 'disabled');
						}
					}		           
		        },
				autowidth : true
			});

	// 表格下方操作
	$(grid_selector).jqGrid('navGrid', pager_selector, {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : false,
		view : false
	});
});

// 人员单选
function selectStaff() {
	SelectStaff_Single(function(data) {
		$("#staffList li").remove();
		$("#staffList").append(dataNodeSelected(data.id, data.name));
		$('#userEditDialog input[name=manager').val(data.id);
	});
}

// 判断用户是否已存在
function checkUser(uid) {
	$.ajax({
		url : home_url + '/security/user/checkUser',
		data : {
			uid : uid
		},
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (data.data > 0)
				return false;
		},
		error : function() {
			$.jGrowl("异常！请重新尝试或者联系管理员!");
		}
	});
}

// 新增、编辑用户
function edit(id) {
	if (id > 0) {
		var rowId = $("#grid_table").jqGrid('getGridParam', 'selrow');
		if (!rowId) {
			$.jGrowl("请选择要编辑的用户");
			return;
		} else {
			location.href = home_url + '/security/user/edit/' + rowId;
		}
	} else {
		location.href = home_url + '/security/user/add';
	}
}

function validateForm() {
	var manager = $("#staffList li").val();
	$('#userEditDialog input[name=manager]').val(manager);
	return $('#userEditDialog').validate({
		rules : {
			manager : {
				required : true
			},
			username : {
				required : true
			},
			password : {
				required : true,
				minlength : 6
			},
			passwordConfirm : {
				equalTo : $('#userEditDialog input[name=password]')
			}
		}

	}).form();
}

// 查询用户
function query() {

	var myDialog = dialog({
		title : '查询用户',
		content : $('#userQueryDialog'),
		height : 'auto',
		width : 500,
		okValue : '确定',
		ok : function() {

			var data = {
				'username' : $('#userQueryDialog input[name=username').val(),
				'fullname' : $('#userQueryDialog input[name=fullname').val()
			};
			var postData = $("#grid_table").jqGrid("getGridParam", "postData");
			// 将查询参数融入postData选项对象
			$.extend(postData, data);
			$("#grid_table").jqGrid('setGridParam').trigger("reloadGrid");

		},
		cancelValue : '取消',
		cancel : function() {
		}
	});
	myDialog.showModal();
}

// 删除用户
function del() {
	var rowId = $("#grid_table").jqGrid('getGridParam', 'selarrrow');
	if (rowId.length == 0) {
		$.jGrowl("请选择要删除的用户");
		return;
	}
	var myDialog = dialog({
		title : '删除用户',
		content : '确定删除用户？',
		height : 'auto',
		width : 500,
		okValue : '确定',
		ok : function() {
			$.ajax({
				url : home_url + '/security/user/update',
				data : {
					'idlist' : rowId,
					'oper' : 'batchdel'
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					$.jGrowl(data.message);
					if (data.code < 0)
						return false;
					$("#grid_table").jqGrid().trigger("reloadGrid");
				},
				error : function() {
					$.jGrowl("异常！请重新尝试或者联系管理员！");
				}
			});
		},
		cancelValue : '取消',
		cancel : function() {
		}
	});
	myDialog.showModal();
}
