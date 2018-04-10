package cn.smarthse.business.model.system;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * 行政区域信息
 * */
@Getter
@Setter
@Document(collection="Sysarea")
public class ASysarea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2508173160215232L;

	/**
     */
	private Long id;

	/**
	 * 父编号
	 */
	private Long pid;
	//邮编
	 private String postcode;

	/**
	 * 简称
	 */
	private String shortName;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;

	/**
	 * 级别(0-国家,1-省,2-市,3-区,4-乡,5-村)
	 */
	private Integer level;

	/**
	 * 热度
	 */
	private Integer sort;

	/**
	 * 省编号
	 */
	private Long provinceid;

	/**
	 * 省名称
	 */
	private String provincename;

	/**
	 * 市编号
	 */
	private Long cityid;

	/**
	 * 市名称
	 */
	private String cityname;

	/**
	 * 区编号
	 */
	private Long districtid;

	/**
	 * 区名称
	 */
	private String districtname;

	/**
	 * 乡编号
	 */
	private Long townid;

	/**
	 * 乡名称
	 */
	private String townname;

	/**
	 * 村编号
	 */
	private Long villageid;

	/**
	 * 村名称
	 */
	private String villagename;

	/**
     */
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName == null ? null : shortName.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude == null ? null : longitude.trim();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude == null ? null : latitude.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Long provinceid) {
		this.provinceid = provinceid;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename == null ? null : provincename.trim();
	}

	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname == null ? null : cityname.trim();
	}

	public Long getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname == null ? null : districtname.trim();
	}

	public Long getTownid() {
		return townid;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setTownid(Long townid) {
		this.townid = townid;
	}

	public String getTownname() {
		return townname;
	}

	public void setTownname(String townname) {
		this.townname = townname == null ? null : townname.trim();
	}

	public Long getVillageid() {
		return villageid;
	}

	public void setVillageid(Long villageid) {
		this.villageid = villageid;
	}

	public String getVillagename() {
		return villagename;
	}

	public void setVillagename(String villagename) {
		this.villagename = villagename == null ? null : villagename.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}