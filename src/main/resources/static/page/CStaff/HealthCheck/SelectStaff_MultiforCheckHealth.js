$(document).ready(function() {
	// alert("树形组织架构加载成功!");
	var setting = {
		view : {
			selectedMulti : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : onClick
		}
	};

	// 弹窗提示配置
	var hsArtDialog = dialog({
		title : '提示',
		id : "hs-dialog",
		fixed : true,
		width : 300,
		height : 50
	});
	// 查询机构
	var orgList = [];
	$.ajax({
		url : home_url + '/org?method=queryOrg',
		data : {},
		type : 'post',
		dataType : 'json',

		success : function(data) {
			var r = data.data;
			for (var i = 0; i < r.length; i++) {
				var item = {
					id : r[i].id,
					pId : r[i].pid,
					name : r[i].fullname
				};
				orgList[i] = item;
			}
			// 初始化树节点
			$.fn.zTree.init($("#orgTree"), setting, orgList);
			// 展开全部
			//expandZtree("orgTree");

			$("#callbackTrigger").bind("change", {}, setTrigger);
		},
		error : function() {
			hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
		}
	});

	// 查询机构的成员
	// getAllOrgMembers();

});

function setTrigger() {
	var zTree = $.fn.zTree.getZTreeObj("#orgTree");
	zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr(
			"checked");
}

// 点击节点，查询该组织的人员信息
function onClick(event, treeId, treeNode, clickFlag) {
	getOrgMembers(treeNode.id);
}

// 获取组织的成员
function getOrgMembers(id) {
	//如果不是复查则查除复查之外的所有
	var typeList=new Array();
	if(type!=3){
		typeList=[1,2,4,5]; 
	}else {
		typeList=[3]; 
	}
	
	$.post(home_url + '/org?method=getOrgMembersForCheckHealth', {
		id : id,
		typeList :typeList,
		checkId:checkId
	}, function(data) {
		$('#_staffList li').remove();
		$.each(data.data, function() {
			if (this.basic.realname != undefined) {
				var myData = this;
				$("#_staffList").append(_staffNode(myData));
			}
		});

		// 重新绑定事件
		$("#_staffList input[type=checkbox]").unbind().change(function() {
			var checked = $(this).is(':checked');
			var id = $(this).val();
			var pname=$(this).attr('pname');
			var isNodeSelected = false;
			$('#_selectedList li').each(function() {
				if ($(this).val() == id) {
					isNodeSelected = true;
					if(!checked)
						$(this).remove();
				}
			});
			if(checked && !isNodeSelected){
				var name = '';
				$.each(data.data, function() {
					if (this.id == id) {
						name = this.basic.realname;
						//break;
					}
				});
				$("#_selectedList").append(_selectStaffNode(id, name, pname));
			}
		});

	});
}

function query() {
	//如果不是复查则查除复查之外的所有
	var typeList=new Array();
	if(type!=3){
		typeList=[1,2,4,5]; 
	}else {
		typeList=[3]; 
	}
	
	var name = $('#simplename').val();
	$.ajax({
		url : home_url + '/org?method=getStaffByNameForCheckHealth',
		data : {
			name : name,
			typeList :typeList,
			checkId:checkId
		},
		async: false,
		type : 'post',
		dataType : 'json',
		success :  function(data) {
			$('#_staffList li').remove();
			$.each(data.data, function() {
				if (this.basic.realname != undefined) {
					var myData = this;
					$("#_staffList").append(_staffNode(myData));
				}
			});

			// 重新绑定事件
			$("#_staffList input[type=checkbox]").unbind().change(function() {
				var checked = $(this).is(':checked');
				var id = $(this).val();
				var pname=$(this).attr('pname');
				var isNodeSelected = false;
				$('#_selectedList li').each(function() {
					if ($(this).val() == id) {
						isNodeSelected = true;
						if(!checked)
							$(this).remove();
					}
				});
				if(checked && !isNodeSelected){
					var name = '';
					$.each(data.data, function() {
						if (this.id == id) {
							name = this.basic.realname;
							//break;
						}
					});
					$("#_selectedList").append(_selectStaffNode(id, name, pname));
				}
			});
		},
		error : function() {
			hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
		}
	});

	//气泡提示
	$('[data-rel=tooltip]').tooltip();
}

// 拼接人员在已选list里的展现
function _selectStaffNode(id, name, pname) {
	var node = '<li pname="'+ pname +'" value="'
			+ id
			+ '" class="info"><span>'
			+ name
			+ '</span><a href="javascript:;" onclick="$(this).li_remove();"><i class="icon-remove white"></i> </a></li>';
	return node;
}

// 拼接人员在备选list里的展现
function _staffNode(data) {
	var node = '<li><div class="checkbox"><label  data-rel="tooltip" data-title="'+ data.organization.fullname +'" ><input pname="'+ data.organization.fullname +'" type="checkbox" value="'
			+ data.id
			+ '" class="ace" name="staff"> <span class="lbl">'
			+ data.basic.realname + '</span></label></div></li>';
	return node;
}
