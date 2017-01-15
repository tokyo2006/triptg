package com.yeoou.tour.web;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.context.Global;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.News;
import com.yeoou.tour.model.PicuterFour;
import com.yeoou.tour.model.Region;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.model.SceneryType;
import com.yeoou.tour.model.ScenerysOfType;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.model.ThemeFlash;
import com.yeoou.tour.model.TripContent;
import com.yeoou.tour.model.TripModel;
import com.yeoou.tour.model.Visa;
import com.yeoou.tour.model.WebSite;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.INewsService;
import com.yeoou.tour.service.IRegionService;
import com.yeoou.tour.service.ISceneryService;
import com.yeoou.tour.service.ISceneryTypeService;
import com.yeoou.tour.service.ITeamService;
import com.yeoou.tour.service.IThemeFlashService;
import com.yeoou.tour.service.ITripContentService;
import com.yeoou.tour.service.ITripModelService;
import com.yeoou.tour.service.IVisaService;
import com.yeoou.tour.service.IWebSiteService;
/**
 * <p>
 * Title: 前台相关主页面展示模块
 * </p>
 * <p>
 * Description: 主页面展示index.shtml,周边游页面indexZby.shtml<br/>
 * 国内游页面indexGny.shtml,出境游页面indexCjy.shtml,企业团队页面<br/>
 * indexQytd.shtml,自由行页面indexZyx.shtml
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ClientPageAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;

	private IThemeFlashService themeFlashService;

	private IRegionService regionService;
	private IAreaService areaService;
	private INewsService newsService;
	private ITeamService teamService;
	private IVisaService visaService;
	private ISceneryService sceneryService;
	private ITripModelService tripModelService;
	private ISceneryTypeService sceneryTypeService;
	private List<Region> regions = new ArrayList<Region>();
	private List<Region> bannerRegions = new ArrayList<Region>();
	private int flag = 0;
	private int bannerFlag = 0;
	private String areaId = "";
	private int searchType = 0;
	private List<TripContent> sy_gnxws;
	private List<TripContent> sy_gwxws;
	private List<TripContent> szsy_ggl ;
	private List<TripContent> szsy_rqjd ;
	private List<TripContent> szsy_rdcs ;
	private List<TripContent> szsy_rqjddb ;
	private List<TripContent> szsy_rdcsdb ;
	private List<TripContent> szsy_zbtj ;
	private List<TripContent> szsy_gntj ;
	private List<TripContent> szsy_cjtj ;
	private List<TripContent> szsy_zyxtj ;
	private List<TripContent> szsy_zbdxt ;
	private List<TripContent> szsy_zbdxz;
	private List<TripContent> szsy_zbdxyz;
	private List<TripContent> szsy_zbdxyxz ;
	private List<TripContent> szsy_zbdxyxy;
	private List<TripContent> szsy_gncxt;
	private List<TripContent> szsy_gncxz;
	private List<TripContent> szsy_gncxyz;
	private List<TripContent> szsy_gncxyxz;
	private List<TripContent> szsy_gncxyxy;
	private List<TripContent> szsy_cjyt;
	private List<TripContent> szsy_cjyz;
	private List<TripContent> szsy_cjyyz;
	private List<TripContent> szsy_cjyyxz;
	private List<TripContent> szsy_cjyyxy;
	private List<TripContent> zyx_jcqt;
	private List<TripContent> zyx_jcqs;
	private List<TripContent> zyx_jcqz;
	private List<TripContent> zyx_jcqx;
	private List<News> zyx_hkjd;
	private List<TripContent> zyx_rdtj;
	private List<TripContent> zyx_cjbbz;
	private List<TripContent> zyx_cjbbys;
	private List<TripContent> zyx_cjbbyxz;
	private List<TripContent> zyx_cjbbyxy;
	private List<TripContent> zyx_rdtj2zs;
	private List<TripContent> zyx_rdtj2zx;
	private List<TripContent> zyx_rdtj2y;
	private List<TripContent> zyx_tw;
	private List<TripContent> zyx_yjgl;
	private List<TripContent> zyx_rqjd;
	private List<TripContent> zyx_gncyz;
	private List<TripContent> zyx_gncyzh;
	private List<TripContent> zyx_gncyy;
	private List<TripContent> zyx_lxszzx;
	private List<TripContent> zyx_lxszzs;
	private List<TripContent> zyx_lxszy;
	private List<TripContent> zyx_rmzyx;
	private List<TripContent> zyx_yx;
	private List<TripContent> gny_zxtu;
	private List<TripContent> gny_rq;
	private List<TripContent> gny_djt;
	private List<TripContent> gny_qjqt;
	private List<TripContent> gny_qjqz;
	private List<TripContent> gny_qjqzs;
	private List<TripContent> gny_qjqz2;
	private List<TripContent> gny_qjqy;
	private List<TripContent> gny_zxz;
	private List<TripContent> gny_zxt;
	private List<TripContent> gny_qjqzx;
	private List<TripContent> gny_zxzs;
	private List<TripContent> gny_zxzx;
	private List<TripContent> gny_zxy;
	private List<TripContent> gny_zxz2;
	private List<TripContent> gny_tht;
	private List<TripContent>	zb_jjs;	
	private List<TripContent>	zb_jjz;	
	private List<TripContent>	zb_jjx;	
	private List<TripContent>	zb_jctj;	
	private List<TripContent>	zb_gcmz;	
	private List<TripContent>	zb_hbwq;	
	private List<TripContent>	zb_ysly;	
	private List<TripContent>	zb_ysws;
	private List<TripContent>	zb_fxb;	
	private List<TripContent>	zb_zttjsz;	
	private List<TripContent>	zb_zttjsys;	
	private List<TripContent>	zb_zttjsyx;		
	private List<TripContent>	zb_zttjx;		
	private List<TripContent>	zb_yryjds;		
	private List<TripContent>	zb_yryjdx;
	private List<TripContent>	zb_yryjdz;
	private List<TripContent>	zb_qytzsz;	
	private List<TripContent>	zb_qytzsys;	
	private List<TripContent>	zb_qytzsyx;	
	private List<TripContent>	zb_qytzx;	
	private List<TripContent>	zb_eryjds;	
	private List<TripContent>	zb_eryjdx;
	private List<TripContent>	zb_eryjdz;
	private List<TripContent>	zb_szywsz;	
	private List<TripContent>	zb_szywsys;
	private List<TripContent>	zb_szywsyx;		
	private List<TripContent>	zb_szywx;	
	private List<TripContent>	zb_sryjds;	
	private List<TripContent>	zb_sryjdx;
	private List<TripContent>	zb_sryjdz;
	private List<TripContent>	zb_rqcs;	
	private List<TripContent>	zb_jqph;	
	private List<TripContent>	cjy_cjyt;	
	private List<TripContent>	cjy_cjys;
	private List<TripContent>	cjy_cjyz;
	private List<TripContent>	cjy_cjyx;
	private List<TripContent>	cjy_jdtj;
	private List<TripContent>	cjy_djt;
	private List<TripContent>	cjy_dj1z;
	private List<TripContent>	cjy_dj1ys;
	private List<TripContent>	cjy_dj2z;
	private List<TripContent>	cjy_dj2ys;	
	private List<TripContent>	cjy_dj3z;
	private List<TripContent>	cjy_dj3ys;
	private List<TripContent>	cjy_dj4ys;
	private List<TripContent>	cjy_dj4z;	
	private List<TripContent>	cjy_dj4yx;	
	private List<TripContent>	cjy_dj1yx;
	private List<TripContent>	cjy_dj2yx;	
	private List<TripContent>	cjy_dj3yx;	
	private List<TripContent>	cjy_jctj;	
	private List<TripContent>	cjy_rmqz;	
	private List<TripContent>	cjy_zj;
	private List<TripContent>	cjy_hwt;
	private List<TripContent>	cjy_hwz;
	private List<TripContent>	cjy_hwy1;
	private List<TripContent>	cjy_hwy2;	
	private List<TripContent>	cjy_hwy3;
	private List<TripContent>	cjy_hwy4;	
	private List<TripContent>	cjy_hwx;
	private List<TripContent>	cjy_gats;	
	private List<TripContent>	cjy_gatx;	
	private List<TripContent>	cjy_dybb;	
	private List<TripContent>	cjy_cyozs;	
	private List<TripContent>	cjy_cyozx;
	private List<TripContent>	qytd_hydd;
	private List<TripContent>	qytd_jczt;
	private List<TripContent>	qytd_jczxy;	
	private List<TripContent>	qytd_jczxz;	
	private List<TripContent>	qytd_jczxzh;	
	private List<TripContent>	qytd_jdjysy;
	private List<TripContent>	qytd_jdjysz;	
	private List<TripContent>	qytd_jdjyx;	
	private List<TripContent>	qytd_jdjzs;	
	private List<TripContent>	qytd_jdjzx;
	private List<TripContent>	qytd_qyfwy;
	private List<TripContent>	qytd_qyfwz;
	private List<TripContent>	qytd_qyfwzh;
	private List<TripContent>	qytd_qyrxs;
	private List<TripContent>	qytd_qyrxx;
	private List<TripContent>	qytd_rdjq;
	private List<TripContent>	qytd_ywgl;	
	private List<TripContent>	cjy_cyozz;
	private List<TripContent>	csjd_banner	;
	private List<TripContent>	csjd_fqt;
	private List<TripContent>	csjd_fqy;	
	private List<TripContent>	csjd_fqz;	
	private List<TripContent>	csjd_fqzs;	
	private List<TripContent>	csjd_fqzx;	
	private List<TripContent>	csjd_gjt;	
	private List<TripContent>	csjd_gjy;	
	private List<TripContent>	csjd_gjz;	
	private List<TripContent>	csjd_gjzs;
	private List<TripContent>	csjd_gjzx;
	private List<TripContent>	csjd_gzt;	
	private List<TripContent>	csjd_gzys;	
	private List<TripContent>	csjd_gzyx;	
	private List<TripContent>	csjd_gzz;	
	private List<TripContent>	csjd_gzzs;	
	private List<TripContent>	csjd_gzzx;	
	private List<TripContent>	csjd_gzzzy;
	private List<TripContent>	csjd_gzzzz;	
	private List<TripContent>	csjd_hdt;	
	private List<TripContent>	csjd_hdys;
	private List<TripContent>	csjd_hdyx;	
	private List<TripContent>	csjd_hdz;
	private List<TripContent>	csjd_hdzs;	
	private List<TripContent>	csjd_hdzx;	
	private List<TripContent>	csjd_hdzz;
	private List<TripContent>	csjd_myt;	
	private List<TripContent>	csjd_myys;
	private List<TripContent>	csjd_myyx;
	private List<TripContent>	csjd_myz;	
	private List<TripContent>	csjd_myzs;	
	private List<TripContent>	csjd_myzx;	
	private List<TripContent>	csjd_qzt;	
	private List<TripContent>	csjd_qzy;	
	private List<TripContent>	csjd_qzz;	
	private List<TripContent>	csjd_qzzs	;	
	private List<TripContent>	csjd_qzzx	;	
	private List<TripContent>	csjd_txt	;
	private List<TripContent>	csjd_txy	;	
	private List<TripContent>	csjd_txz	;	
	private List<TripContent>	csjd_txzs	;	
	private List<TripContent>	csjd_txzx	;
	private List<TripContent>	csjd_tyt	;	
	private List<TripContent>	csjd_tyy	;	
	private List<TripContent>	csjd_tyz	;	
	private List<TripContent>	csjd_tyzs	;	
	private List<TripContent>	csjd_tyzx	;	
	private List<TripContent>	csjd_wst	;	
	private List<TripContent>	csjd_wsys	;	
	private List<TripContent>	csjd_wsyx	;	
	private List<TripContent>	csjd_wsz	;	
	private List<TripContent>	csjd_wszs	;
	private List<TripContent>	csjd_wszx	;	
	private List<TripContent>	csjd_wszz	;	
	private List<TripContent>	csjd_yst	;	
	private List<TripContent>	csjd_ysys	;	
	private List<TripContent>	csjd_ysyx	;
	private List<TripContent>	csjd_ysz	;	
	private List<TripContent>	csjd_yszs	;	
	private List<TripContent>	csjd_yszx	;	
	private List<TripContent>	csjd_yszzy	;
	private List<TripContent>	csjd_yszzz	;	
	private List<TripContent>   csjd_gj1;
	private List<TripContent>   csjd_gj2;
	private List<TripContent>   csjd_gj3;
	private List<TripContent>   csjd_gj4;
	private List<TripContent>   csjd_gj5;
	private List<TripContent>   csjd_gj6;
	private List<TripContent> qytd_yx;
	private TripModel csjd_class_gj1;
	private TripModel csjd_class_gj2;
	private TripModel csjd_class_gj3;
	private TripModel csjd_class_gj4;
	private TripModel csjd_class_gj5;
	private TripModel csjd_class_gj6;
	private List<TripContent>   szsy_yx;
	private List<ThemeFlash>    sy;
	private List<ThemeFlash>    zb;
	private List<ThemeFlash>    gn;
	private List<ThemeFlash>    cj;
	private List<ThemeFlash>	zyx;
	private List<ThemeFlash>    qytd;
	private List<ThemeFlash>    csjd;
	private List<ScenerysOfType> csjd_jxtj;
	private List<String> searchList = new ArrayList<String>();
	private IWebSiteService webSiteService;
	private List<TripContent> gg_ss;
	private List<Team> gny_tejia;
	private WebSite webSite ;
	private ITripContentService tripContentService;
	private List<News> sy_gnxw;
	private List<News> sy_gwxw;
	private List<Visa> sy_qzxx;
	private List<News> zb_szzb;
	private List<News> gny_gnxw;
	private List<News> gny_zxph;
	private List<News> gny_qzjt;
	private List<News> gny_qlmy;
	private List<News> gny_jczt;
	private List<News> cjy_rdxw;
	private List<News> cjy_zysx;
	private List<News> gny_gytt;
	private List<News> gny_bbzy;
	private List<News> gny_btly;
	private List<News> rightHelps;
	private List<Region> guoneiList = new ArrayList<Region>();
	private List<Region> zhoubianList = new ArrayList<Region>();
	private List<Region> chujingList = new ArrayList<Region>();
	private List<Region> ziyouxingList = new ArrayList<Region>();
	/**
	 * 获取所有旅游类别并进行显示模块
	 *
	 */
	public void getAllRegion()
	{
		String parentAreaId = "";
		if(areaId.equals(""))
		{
			parentAreaId = "402880e51a512576011a512b60fb0004";
		}
		else
		{
			Area area = new Area();
			area = (Area)areaService.get(areaId);
			if(area.getName().equals("北京")||area.getName().equals("重庆")||area.getName().equals("天津")||area.getName().equals("上海")||area.getName().equals("香港")||area.getName().equals("澳门"))
			{
				parentAreaId = area.getAreaId();
			}
			else
			{
				parentAreaId = area.getParent();
			}
			parentAreaId = areaId;
		}
		Region region = regionService.getRegionByAreaId(parentAreaId,1);
		regions = regionService.getRegionsByParent(region.getRegionId(),"flag",true);
		int size = regions.size();
		for(int i=0;i<size;i++)
		{
			regions.get(i).setChildrenList(regionService.getChildren(regions.get(i).getRegionId()));
			int length = regions.get(i).getChildrenList().size();
			for(int j=0;j<length;j++)
			{
				regions.get(i).getChildrenList().get(j).setChildrenList(regionService.getChildren(regions.get(i).getChildrenList().get(j).getRegionId()));
			}
		}
		for(int i=0;i<size;i++)
		{
			if(regions.get(i).getFlag()==Global.GUONEI)
			{
				this.guoneiList.addAll(regions.get(i).getChildrenList());
			}
			if(regions.get(i).getFlag()==Global.CHUJING)
			{
				this.chujingList.addAll(regions.get(i).getChildrenList());
			}
			if(regions.get(i).getFlag()==Global.ZHOUBIAN)
			{
				this.zhoubianList.addAll(regions.get(i).getChildrenList());
			}
			if(regions.get(i).getFlag()==Global.CHUJING_FREE)
			{
				this.ziyouxingList.addAll(regions.get(i).getChildrenList());
			}
			
			
		}
	}
	/**
	 * 右边导航类别显示模块
	 * @param flag
	 */
	public void getRightRegions(int flag)
	{
		String parentAreaId = "";
		areaId = this.getIPAddress();
		Area area = new Area();
		area = (Area)areaService.get(areaId);
		if(area.getName().equals("北京")||area.getName().equals("重庆")||area.getName().equals("天津")||area.getName().equals("上海")||area.getName().equals("香港")||area.getName().equals("澳门"))
		{
			parentAreaId = area.getAreaId();
		}
		else
		{
			parentAreaId = area.getParent();
		}
		Region region = regionService.getRegionByAreaId(parentAreaId,1);
		Region parent = new Region();
		if(region!=null)
		{
			regions = regionService.getRegionsByParent(region.getRegionId(),"flag",true);
			if(regions!=null)
			{
				int size = regions.size();
				for(int i=0;i<size;i++)
				{
					if(regions.get(i).getFlag()==flag)
					{
						parent = regions.get(i);
					}
				}
				regions = new ArrayList<Region>();
				regions = regionService.getChildren(parent.getRegionId());
				size = regions.size();
				for(int i=0;i<size;i++)
				{
					regions.get(i).setChildrenList(regionService.getChildren(regions.get(i).getRegionId()));
				}
			}
		}
	}
	/**
	 * @see ClientAreaAction#getBannaerInfo()
	 * @return
	 */
	public String getBannaerInfo()
	{
		String areaId = this.getIPAddress();
		webSite = webSiteService.getWebSiteByArea(areaId);
		searchList.add("线路搜索");
		searchList.add("景点搜索");
		gg_ss = tripContentService.getTripContentList("", "gg_ss");
		return SUCCESS;
	}
	/**
	 * 城市景点页面详细展示，包括网站信息，详细页面标签相关内容
	 * @return
	 */
	public String getCsjdInf()
	{
		getBannaerInfo();
		this.bannerFlag = 8;
		List<SceneryType> stList = (List<SceneryType>)sceneryTypeService.getAll();
		int length = stList.size();
		csjd_jxtj = new ArrayList<ScenerysOfType>();
		for(int i=0;i<length;i++)
		{
			ScenerysOfType sot = new ScenerysOfType();
			List<Scenery> scList = new ArrayList<Scenery>();
			scList = sceneryService.getSceneryByType(stList.get(i).getTypeId(), 12);
			sot.setSt(stList.get(i));
			sot.setScList(scList);
			this.csjd_jxtj.add(sot);
		}
		this.csjd = this.themeFlashService.getThemeByFlashType("csjd");
		csjd_banner	= tripContentService.getTripContentList("", "csjd_banner");
		csjd_fqt	= tripContentService.getTripContentList("", "csjd_fqt");
		csjd_fqy	= tripContentService.getTripContentList("", "csjd_fqy");	
		csjd_fqz	= tripContentService.getTripContentList("", "csjd_fqz");	
		csjd_fqzs	= tripContentService.getTripContentList("", "csjd_fqzs");	
		csjd_fqzx	= tripContentService.getTripContentList("", "csjd_fqzx");	
		csjd_gjt	= tripContentService.getTripContentList("", "csjd_gjt");	
		csjd_gjy	= tripContentService.getTripContentList("", "csjd_gjy");	
		csjd_gjz	= tripContentService.getTripContentList("", "csjd_gjz");	
		csjd_gjzs	= tripContentService.getTripContentList("", "csjd_gjzs");
		csjd_gjzx	= tripContentService.getTripContentList("", "csjd_gjzx");
		csjd_gzt	= tripContentService.getTripContentList("", "csjd_gzt");	
		csjd_gzys	= tripContentService.getTripContentList("", "csjd_gzys");	
		csjd_gzyx	= tripContentService.getTripContentList("", "csjd_gzyx");	
		csjd_gzz	= tripContentService.getTripContentList("", "csjd_gzz");	
		csjd_gzzs	= tripContentService.getTripContentList("", "csjd_gzzs");	
		csjd_gzzx	= tripContentService.getTripContentList("", "csjd_gzzx");	
		csjd_gzzzy	= tripContentService.getTripContentList("", "csjd_gzzzy");
		csjd_gzzzz	= tripContentService.getTripContentList("", "csjd_gzzzz");	
		csjd_hdt	= tripContentService.getTripContentList("", "csjd_hdt");	
		csjd_hdys	= tripContentService.getTripContentList("", "csjd_hdys");
		csjd_hdyx	= tripContentService.getTripContentList("", "csjd_hdyx");	
		csjd_hdz	= tripContentService.getTripContentList("", "csjd_hdz");
		csjd_hdzs	= tripContentService.getTripContentList("", "csjd_hdzs");	
		csjd_hdzx	= tripContentService.getTripContentList("", "csjd_hdzx");	
		csjd_hdzz	= tripContentService.getTripContentList("", "csjd_hdzz");
		csjd_myt	= tripContentService.getTripContentList("", "csjd_myt");	
		csjd_myys	= tripContentService.getTripContentList("", "csjd_myys");
		csjd_myyx	= tripContentService.getTripContentList("", "csjd_myyx");
		csjd_myz	= tripContentService.getTripContentList("", "csjd_myz");	
		csjd_myzs	= tripContentService.getTripContentList("", "csjd_myzs");	
		csjd_myzx	= tripContentService.getTripContentList("", "csjd_myzx");	
		csjd_qzt	= tripContentService.getTripContentList("", "csjd_qzt");	
		csjd_qzy	= tripContentService.getTripContentList("", "csjd_qzy");	
		csjd_qzz	= tripContentService.getTripContentList("", "csjd_qzz");	
		csjd_qzzs	= tripContentService.getTripContentList("", "csjd_qzzs");	
		csjd_qzzx	= tripContentService.getTripContentList("", "csjd_qzzx");	
		csjd_txt	= tripContentService.getTripContentList("", "csjd_txt");
		csjd_txy	= tripContentService.getTripContentList("", "csjd_txy");	
		csjd_txz	= tripContentService.getTripContentList("", "csjd_txz");	
		csjd_txzs	= tripContentService.getTripContentList("", "csjd_txzs");	
		csjd_txzx	= tripContentService.getTripContentList("", "csjd_txzx");
		csjd_tyt	= tripContentService.getTripContentList("", "csjd_tyt");	
		csjd_tyy	= tripContentService.getTripContentList("", "csjd_tyy");	
		csjd_tyz	= tripContentService.getTripContentList("", "csjd_tyz");	
		csjd_tyzs	= tripContentService.getTripContentList("", "csjd_tyzs");	
		csjd_tyzx	= tripContentService.getTripContentList("", "csjd_tyzx");	
		csjd_wst	= tripContentService.getTripContentList("", "csjd_wst");	
		csjd_wsys	= tripContentService.getTripContentList("", "csjd_wsys");	
		csjd_wsyx	= tripContentService.getTripContentList("", "csjd_wsyx");	
		csjd_wsz	= tripContentService.getTripContentList("", "csjd_wsz");	
		csjd_wszs	= tripContentService.getTripContentList("", "csjd_wszs");
		csjd_wszx	= tripContentService.getTripContentList("", "csjd_wszx");	
		csjd_wszz	= tripContentService.getTripContentList("", "csjd_wszz");	
		csjd_yst	= tripContentService.getTripContentList("", "csjd_yst");	
		csjd_ysys	= tripContentService.getTripContentList("", "csjd_ysys");	
		csjd_ysyx	= tripContentService.getTripContentList("", "csjd_ysyx");
		csjd_ysz	= tripContentService.getTripContentList("", "csjd_ysz");	
		csjd_yszs	= tripContentService.getTripContentList("", "csjd_yszs");	
		csjd_yszx	= tripContentService.getTripContentList("", "csjd_yszx");	
		csjd_yszzy	= tripContentService.getTripContentList("", "csjd_yszzy");
		csjd_yszzz	= tripContentService.getTripContentList("", "csjd_yszzz");	
		this.csjd_gj1 = tripContentService.getTripContentList("", "csjd_gj1");	
		this.csjd_gj2 = tripContentService.getTripContentList("", "csjd_gj2");	
		this.csjd_gj3 = tripContentService.getTripContentList("", "csjd_gj3");	
		this.csjd_gj4 = tripContentService.getTripContentList("", "csjd_gj4");	
		this.csjd_gj5 = tripContentService.getTripContentList("", "csjd_gj5");	
		this.csjd_gj6 = tripContentService.getTripContentList("", "csjd_gj6");	
		this.csjd_class_gj1 = tripModelService.getTripModelByName("csjd_gj1");
		this.csjd_class_gj2 = tripModelService.getTripModelByName("csjd_gj2");
		this.csjd_class_gj3 = tripModelService.getTripModelByName("csjd_gj3");
		this.csjd_class_gj4 = tripModelService.getTripModelByName("csjd_gj4");
		this.csjd_class_gj5 = tripModelService.getTripModelByName("csjd_gj5");
		this.csjd_class_gj6 = tripModelService.getTripModelByName("csjd_gj6");
		return SUCCESS;
	}
	/**
	 * 网站主页页面详细展示，包括网站信息，详细页面标签相关内容<br/>
	 * 所有旅游景点类别索引（下部）{@link #getAllRegion()}
	 * @return
	 */
	public String getHomePageInf()
	{
		this.bannerFlag = 1;
		getBannaerInfo();
		getAllRegion();
		String areaId = this.getIPAddress();
		sy_gnxw = newsService.getNewsByNewsTypeParentNum("f2d041b81fe688c0011fef5803710140", 5);
		sy_gwxw= newsService.getNewsByNewsTypeParentNum("f2d041b81fe688c0011fef644d22014e",5);
		sy_qzxx= visaService.getVisaForNum(10);
		this.sy = this.themeFlashService.getThemeByFlashType("sy");
		this.szsy_yx = tripContentService.getTripContentList("", "szsy_yx");
		this.sy_gnxws = tripContentService.getTripContentList("", "sy_gnxws");
		this.sy_gwxws = tripContentService.getTripContentList("", "sy_gwxws");
		this.szsy_cjtj = tripContentService.getTripContentList(areaId, "szsy_cjtj");
		this.szsy_cjyt = tripContentService.getTripContentList("", "szsy_cjyt");
		this.szsy_cjyyxy = tripContentService.getTripContentList("", "szsy_cjyyxy");
		this.szsy_cjyyxz = tripContentService.getTripContentList("", "szsy_cjyyxz");
		this.szsy_cjyyz = tripContentService.getTripContentList(areaId, "szsy_cjyyz");
		this.szsy_cjyz = tripContentService.getTripContentList("", "szsy_cjyz");
		this.szsy_ggl = tripContentService.getTripContentList("", "szsy_ggl");
		this.szsy_gncxt = tripContentService.getTripContentList("", "szsy_gncxt");
		this.szsy_gncxyxy = tripContentService.getTripContentList("", "szsy_gncxyxy");
		this.szsy_gncxyxz = tripContentService.getTripContentList("", "szsy_gncxyxz");
		this.szsy_gncxyz = tripContentService.getTripContentList(areaId, "szsy_gncxyz");
		this.szsy_gncxz = tripContentService.getTripContentList("", "szsy_gncxz");
		this.szsy_gntj = tripContentService.getTripContentList(areaId, "szsy_gntj");
		this.szsy_rdcs = tripContentService.getTripContentList("", "szsy_rdcs");
		this.szsy_rdcsdb = tripContentService.getTripContentList("", "szsy_rdcsdb");
		this.szsy_zbdxt = tripContentService.getTripContentList("", "szsy_zbdxt");
		this.szsy_zbdxyxy = tripContentService.getTripContentList("", "szsy_zbdxyxy");
		this.szsy_zbdxyxz = tripContentService.getTripContentList("", "szsy_zbdxyxz");
		this.szsy_zbdxyz = tripContentService.getTripContentList(areaId, "szsy_zbdxyz");
		this.szsy_zbdxz = tripContentService.getTripContentList("", "szsy_zbdxz");
		this.szsy_zbtj = tripContentService.getTripContentList(areaId, "szsy_zbtj");
		this.szsy_zyxtj = tripContentService.getTripContentList(areaId, "szsy_zyxtj");
	    this.szsy_rqjd = tripContentService.getTripContentList("", "szsy_rqjd");
	    this.szsy_rqjddb = tripContentService.getTripContentList("", "szsy_rqjddb");
	    this.rightHelps = newsService.getNewsByRemark("helpL1");
		return SUCCESS;
	}
	/**
	 * 企业团队页面详细展示，包括网站信息，详细页面标签相关内容
	 * @return
	 */
	public String getQytdInf()
	{
		this.bannerFlag = 6;
		getBannaerInfo();
		this.qytd = this.themeFlashService.getThemeByFlashType("qytd");
		qytd_hydd= tripContentService.getTripContentList("", "qytd_hydd");
		qytd_jczt= tripContentService.getTripContentList("", "qytd_jczt");
		qytd_jczxy= tripContentService.getTripContentList("", "qytd_jczxy");	
		qytd_jczxz= tripContentService.getTripContentList("", "qytd_jczxz");	
		qytd_jczxzh= tripContentService.getTripContentList("", "qytd_jczxzh");	
		qytd_jdjysy= tripContentService.getTripContentList("", "qytd_jdjysy");
		qytd_jdjysz= tripContentService.getTripContentList("", "qytd_jdjysz");	
		qytd_jdjyx= tripContentService.getTripContentList("", "qytd_jdjyx");	
		qytd_jdjzs= tripContentService.getTripContentList("", "qytd_jdjzs");	
		qytd_jdjzx= tripContentService.getTripContentList("", "qytd_jdjzx");
		qytd_qyfwy= tripContentService.getTripContentList("", "qytd_qyfwy");
		qytd_qyfwz= tripContentService.getTripContentList("", "qytd_qyfwz");
		qytd_qyfwzh= tripContentService.getTripContentList("", "qytd_qyfwzh");
		qytd_qyrxs= tripContentService.getTripContentList("", "qytd_qyrxs");
		qytd_qyrxx= tripContentService.getTripContentList("", "qytd_qyrxx");
		qytd_rdjq= tripContentService.getTripContentList("", "qytd_rdjq");
		qytd_ywgl= tripContentService.getTripContentList("", "qytd_ywgl");
		qytd_yx = tripContentService.getTripContentList("", "qytd_yx");
		
		return SUCCESS;
	}
	/**
	 * 出境游页面详细展示，包括网站信息，详细页面标签相关内容
	 * @return
	 */
	public String getCjyInf()
	{
		String areaId = this.getIPAddress();
		getRightRegions(3);
		getBannaerInfo();
		this.bannerFlag = 4;
		cjy_rdxw = newsService.getNewsByNewsTypeForNum("f2d041b81fe688c0011fefbd6e36019e",7);
		cjy_zysx= newsService.getNewsByNewsTypeForNum("f2d041b81fe688c0011fefbd9d26019f",6);
		this.cj = this.themeFlashService.getThemeByFlashType("cj");
		cjy_cjyt= tripContentService.getTripContentList("", "cjy_cjyt");	
		cjy_cjys= tripContentService.getTripContentList("", "cjy_cjys")	;
		cjy_cjyz= tripContentService.getTripContentList("", "cjy_cjyz");
		cjy_cjyx= tripContentService.getTripContentList(areaId, "cjy_cjyx")	;
		cjy_jdtj= tripContentService.getTripContentList("", "cjy_jdtj")	;
		cjy_djt= tripContentService.getTripContentList("", "cjy_djt")	;
		cjy_dj1z= tripContentService.getTripContentList("", "cjy_dj1z")	;
		cjy_dj1ys= tripContentService.getTripContentList("", "cjy_dj1ys");
		cjy_dj2z= tripContentService.getTripContentList("", "cjy_dj2z")	;
		cjy_dj2ys= tripContentService.getTripContentList("", "cjy_dj2ys")	;	
		cjy_dj3z= tripContentService.getTripContentList("", "cjy_dj3z")	;
		cjy_dj3ys= tripContentService.getTripContentList("", "cjy_dj3ys")	;
		cjy_dj4ys= tripContentService.getTripContentList("", "cjy_dj4ys")	;
		cjy_dj4z= tripContentService.getTripContentList("", "cjy_dj4z")	;	
		cjy_dj4yx	= tripContentService.getTripContentList("", "cjy_dj4yx");	
		cjy_dj1yx	= tripContentService.getTripContentList("", "cjy_dj1yx");
		cjy_dj2yx= tripContentService.getTripContentList("", "cjy_dj2yx")	;	
		cjy_dj3yx= tripContentService.getTripContentList("", "cjy_dj3yx")	;	
		cjy_jctj= tripContentService.getTripContentList("", "cjy_jctj")	;	
		cjy_rmqz= tripContentService.getTripContentList("", "cjy_rmqz")	;	
		cjy_zj		= tripContentService.getTripContentList(areaId, "cjy_zj");
		cjy_hwt	= tripContentService.getTripContentList("", "cjy_hwt");
		cjy_hwz	= tripContentService.getTripContentList("", "cjy_hwz")	;
		cjy_hwy1= tripContentService.getTripContentList("", "cjy_hwy1")	;
		cjy_hwy2= tripContentService.getTripContentList("", "cjy_hwy2")	;	
		cjy_hwy3= tripContentService.getTripContentList("", "cjy_hwy3")	;
		cjy_hwy4= tripContentService.getTripContentList("", "cjy_hwy4")	;	
		cjy_hwx	= tripContentService.getTripContentList("", "cjy_hwx")	;
		cjy_gats= tripContentService.getTripContentList("", "cjy_gats")	;	
		cjy_gatx= tripContentService.getTripContentList("", "cjy_gatx")	;	
		cjy_dybb= tripContentService.getTripContentList("", "cjy_dybb")	;	
		cjy_cyozs= tripContentService.getTripContentList("", "cjy_cyozs")	;	
		cjy_cyozx	= tripContentService.getTripContentList("", "cjy_cyozx");
		cjy_cyozz	= tripContentService.getTripContentList("", "cjy_cyozz");
		return SUCCESS;
	}
	/**
	 * 国内游页面详细展示，包括网站信息，详细页面标签相关内容
	 * @return
	 */
	public String getGnyInf()
	{
		this.bannerFlag = 3;
		getBannaerInfo();
		String areaId = this.getIPAddress();
		getRightRegions(2);
		gny_tht = tripContentService.getTripContentList("", "gny_tht");
		gny_gnxw = newsService.getNewsByNewsTypeParentNum("f2d041b81fe688c0011fef5803710140",6);
		gny_zxph = newsService.getNewsByNewsTypeParentNum("402881ff1fe4f8bf011fe51581260010",6);
		gny_qzjt = newsService.getNewsByNewsTypeForNum("f2d041b81fe688c0011fefb60bb50197",6);
		gny_qlmy = newsService.getNewsByNewsTypeForNum("f2d041b81fe688c0011fefb666a70198",6);
		gny_jczt = newsService.getNewsByNewsTypeForNum("f2d041b81fe688c0011fefbc1b1a019c", 9);
		gny_gytt= newsService.getNewsByNewsTypeForNum("f2d041b81fe688c0011fefbc5ca7019d", 9);
		gny_bbzy = newsService.getNewsByNewsTypeForNum("f2d041b81fe688c0011fefb897a0019b", 6);
		gny_btly = newsService.getNewsByNewsTypeForNum("f2d041b81fe688c0011fefb6a0e10199", 6);
		this.gn = this.themeFlashService.getThemeByFlashType("gn");
		gny_zxtu = tripContentService.getTripContentList("", "gny_zxtu");
		gny_rq = tripContentService.getTripContentList("", "gny_rq");
		gny_djt = tripContentService.getTripContentList("", "gny_djt");
		gny_qjqt = tripContentService.getTripContentList("", "gny_qjqt");
		gny_qjqz = tripContentService.getTripContentList("", "gny_qjqz");
		gny_qjqzs = tripContentService.getTripContentList("", "gny_qjqzs");
		gny_qjqz2 = tripContentService.getTripContentList("", "gny_qjqz2");
		gny_qjqy = tripContentService.getTripContentList("", "gny_qjqy");
		gny_zxz = tripContentService.getTripContentList("", "gny_zxz");
		gny_zxt = tripContentService.getTripContentList("", "gny_zxt");
		gny_qjqzx = tripContentService.getTripContentList("", "gny_qjqzx");
		gny_zxzs = tripContentService.getTripContentList("", "gny_zxzs");
		gny_zxzx = tripContentService.getTripContentList("", "gny_zxzx");
		gny_zxy = tripContentService.getTripContentList("", "gny_zxy");
		gny_zxz2 = tripContentService.getTripContentList("", "gny_zxz2");
		gny_tejia = teamService.getTeamByTejia(Global.GUONEI,areaId);
		List<PicuterFour> pfList = new ArrayList<PicuterFour>();
		if(gny_tejia!=null)
		{
			int size = gny_tejia.size();
			for(int i=0;i<size;i++)
			{
				pfList = PicuterFour.unpackagePicFour(gny_tejia.get(i).getLine().getPicArea());
		    	if(pfList!=null)
		    	{
		    		gny_tejia.get(i).setOrgUrl(pfList.get(0).getBreUrl());
		    		gny_tejia.get(i).setBreUrl(pfList.get(0).getUrl());
		    	}
			}
		}
		return SUCCESS;
	}
	/**
	 * 城周边游页面详细展示，包括网站信息，详细页面标签相关内容
	 * @return
	 */
	public String getZbInf()
	{
		String areaId = this.getIPAddress();
		this.bannerFlag=2;
		getBannaerInfo();
		getRightRegions(1);
		zb_szzb =  newsService.getNewsByNewsTypeParentNum("402881ff1fe4f8bf011fe51581260010", 11);
		this.zb = this.themeFlashService.getThemeByFlashType("zb");
		zb_jjs= tripContentService.getTripContentList("", "zb_jjs");	
		zb_jjz= tripContentService.getTripContentList("", "zb_jjz");	
		zb_jjx= tripContentService.getTripContentList(areaId, "zb_jjx");	
		zb_jctj= tripContentService.getTripContentList("", "zb_jctj");	
		zb_gcmz= tripContentService.getTripContentList("", "zb_gcmz");	
		zb_hbwq= tripContentService.getTripContentList("", "zb_hbwq");	
		zb_ysly= tripContentService.getTripContentList(areaId, "zb_ysly");
		zb_ysws= tripContentService.getTripContentList(areaId, "zb_ysws");
		zb_fxb= tripContentService.getTripContentList("", "zb_fxb");	
		zb_zttjsz= tripContentService.getTripContentList("", "zb_zttjsz");	
		zb_zttjsys= tripContentService.getTripContentList("", "zb_zttjsys");	
		zb_zttjsyx= tripContentService.getTripContentList("", "zb_zttjsyx");		
		zb_zttjx= tripContentService.getTripContentList("", "zb_zttjx");		
		zb_yryjds= tripContentService.getTripContentList(areaId, "zb_yryjds");		
		zb_yryjdx= tripContentService.getTripContentList(areaId, "zb_yryjdx");	
		zb_yryjdz= tripContentService.getTripContentList(areaId, "zb_yryjdz");
		zb_qytzsz= tripContentService.getTripContentList("", "zb_qytzsz");	
		zb_qytzsys= tripContentService.getTripContentList("", "zb_qytzsys");	
		zb_qytzsyx= tripContentService.getTripContentList("", "zb_qytzsyx");	
		zb_qytzx= tripContentService.getTripContentList("", "zb_qytzx");	
		zb_eryjds= tripContentService.getTripContentList(areaId, "zb_eryjds");	
		zb_eryjdx= tripContentService.getTripContentList(areaId, "zb_eryjdx");
		zb_eryjdz= tripContentService.getTripContentList(areaId, "zb_eryjdz");	
		zb_szywsz= tripContentService.getTripContentList(areaId, "zb_szywsz");	
		zb_szywsys= tripContentService.getTripContentList(areaId, "zb_szywsys");
		zb_szywsyx= tripContentService.getTripContentList(areaId, "zb_szywsyx");		
		zb_szywx= tripContentService.getTripContentList(areaId, "zb_szywx");	
		zb_sryjds= tripContentService.getTripContentList(areaId, "zb_sryjds");	
		zb_sryjdx= tripContentService.getTripContentList(areaId, "zb_sryjdx");
		zb_sryjdz= tripContentService.getTripContentList(areaId, "zb_sryjdz");
		zb_rqcs= tripContentService.getTripContentList("", "zb_rqcs");	
		zb_jqph= tripContentService.getTripContentList("", "zb_jqph");	
		return SUCCESS;
	}
	/**
	 * 自由行页面详细展示，包括网站信息，详细页面标签相关内容
	 * @return
	 */
	public String getZyxInf()
	{
		this.bannerFlag = 5;
		getRightRegions(4);
		String areaId = this.getIPAddress();
		getBannaerInfo();
		List<News> temp1 = new ArrayList<News>();
		temp1 = newsService.getNewsByNewsTypeParentNum("f2d041b81fe688c0011fef5966ca0142", 5);
		List<News> temp2 = new ArrayList<News>();
		temp2 = newsService.getNewsByNewsTypeParentNum("f2d041b81fe688c0011fef58987e0141", 5);
		this.zyx = this.themeFlashService.getThemeByFlashType("zyx");
		zyx_jcqt = tripContentService.getTripContentList("", "zyx_jcqt");
		zyx_jcqs= tripContentService.getTripContentList("", "zyx_jcqs");
		zyx_jcqz = tripContentService.getTripContentList("", "zyx_jcqz");
		zyx_jcqx = tripContentService.getTripContentList(areaId, "zyx_jcqx");
		zyx_hkjd = new ArrayList<News>();
		zyx_hkjd.addAll(temp1);
		zyx_hkjd.addAll(temp2);
		zyx_rdtj = tripContentService.getTripContentList("", "zyx_rdtj");
		zyx_cjbbz = tripContentService.getTripContentList("", "zyx_cjbbz");
		zyx_cjbbys = tripContentService.getTripContentList("", "zyx_cjbbys");
		zyx_cjbbyxz = tripContentService.getTripContentList("", "zyx_cjbbyxz");
		zyx_cjbbyxy = tripContentService.getTripContentList("", "zyx_cjbbyxy");
		zyx_rdtj2zs = tripContentService.getTripContentList("", "zyx_rdtj2zs");
		zyx_rdtj2zx = tripContentService.getTripContentList("", "zyx_rdtj2zx");
		zyx_rdtj2y = tripContentService.getTripContentList("", "zyx_rdtj2y");
		zyx_tw = tripContentService.getTripContentList("", "zyx_tw");
		zyx_yjgl = tripContentService.getTripContentList("", "zyx_yjgl");
		zyx_rqjd = tripContentService.getTripContentList("", "zyx_rqjd");
		zyx_gncyz = tripContentService.getTripContentList("", "zyx_gncyz");
		zyx_gncyzh = tripContentService.getTripContentList("", "zyx_gncyzh");
		zyx_gncyy = tripContentService.getTripContentList("", "zyx_gncyy");
		zyx_lxszzx = tripContentService.getTripContentList("", "zyx_lxszzx");
		zyx_lxszzs = tripContentService.getTripContentList("", "zyx_lxszzs");
		zyx_lxszy = tripContentService.getTripContentList("", "zyx_lxszy");
		zyx_rmzyx = tripContentService.getTripContentList("", "zyx_rmzyx");
		zyx_yx = tripContentService.getTripContentList("", "zyx_yx");
		return SUCCESS;
	}
	
	public List<TripContent> getQytd_jczt()
	{
		return qytd_jczt;
	}
	public void setQytd_jczt(List<TripContent> qytd_jczt)
	{
		this.qytd_jczt = qytd_jczt;
	}
	public List<TripContent> getQytd_jczxy()
	{
		return qytd_jczxy;
	}
	
	public List<News> getCjy_rdxw() {
		return cjy_rdxw;
	}
	public void setCjy_rdxw(List<News> cjy_rdxw) {
		this.cjy_rdxw = cjy_rdxw;
	}
	public List<News> getCjy_zysx() {
		return cjy_zysx;
	}
	public void setCjy_zysx(List<News> cjy_zysx) {
		this.cjy_zysx = cjy_zysx;
	}
	public List<News> getGny_bbzy() {
		return gny_bbzy;
	}
	public void setGny_bbzy(List<News> gny_bbzy) {
		this.gny_bbzy = gny_bbzy;
	}
	public List<News> getGny_btly() {
		return gny_btly;
	}
	public void setGny_btly(List<News> gny_btly) {
		this.gny_btly = gny_btly;
	}
	public List<News> getGny_gnxw() {
		return gny_gnxw;
	}
	public void setGny_gnxw(List<News> gny_gnxw) {
		this.gny_gnxw = gny_gnxw;
	}
	public List<News> getGny_gytt() {
		return gny_gytt;
	}
	public void setGny_gytt(List<News> gny_gytt) {
		this.gny_gytt = gny_gytt;
	}
	public List<News> getGny_jczt() {
		return gny_jczt;
	}
	public void setGny_jczt(List<News> gny_jczt) {
		this.gny_jczt = gny_jczt;
	}
	public List<News> getGny_qlmy() {
		return gny_qlmy;
	}
	public void setGny_qlmy(List<News> gny_qlmy) {
		this.gny_qlmy = gny_qlmy;
	}
	public List<News> getGny_qzjt() {
		return gny_qzjt;
	}
	public void setGny_qzjt(List<News> gny_qzjt) {
		this.gny_qzjt = gny_qzjt;
	}
	public List<News> getGny_zxph() {
		return gny_zxph;
	}
	public void setGny_zxph(List<News> gny_zxph) {
		this.gny_zxph = gny_zxph;
	}
	public INewsService getNewsService() {
		return newsService;
	}
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	public List<News> getSy_gnxw() {
		return sy_gnxw;
	}
	public void setSy_gnxw(List<News> sy_gnxw) {
		this.sy_gnxw = sy_gnxw;
	}
	public List<News> getSy_gwxw() {
		return sy_gwxw;
	}
	public void setSy_gwxw(List<News> sy_gwxw) {
		this.sy_gwxw = sy_gwxw;
	}
	public List<Visa> getSy_qzxx() {
		return sy_qzxx;
	}
	public void setSy_qzxx(List<Visa> sy_qzxx) {
		this.sy_qzxx = sy_qzxx;
	}
	public IVisaService getVisaService() {
		return visaService;
	}
	public void setVisaService(IVisaService visaService) {
		this.visaService = visaService;
	}
	public List<News> getZb_szzb() {
		return zb_szzb;
	}
	public void setZb_szzb(List<News> zb_szzb) {
		this.zb_szzb = zb_szzb;
	}
	public void setQytd_jczxy(List<TripContent> qytd_jczxy)
	{
		this.qytd_jczxy = qytd_jczxy;
	}
	public List<TripContent> getQytd_jczxz()
	{
		return qytd_jczxz;
	}
	public void setQytd_jczxz(List<TripContent> qytd_jczxz)
	{
		this.qytd_jczxz = qytd_jczxz;
	}
	public List<TripContent> getQytd_jczxzh()
	{
		return qytd_jczxzh;
	}
	public void setQytd_jczxzh(List<TripContent> qytd_jczxzh)
	{
		this.qytd_jczxzh = qytd_jczxzh;
	}
	public List<TripContent> getQytd_jdjysy()
	{
		return qytd_jdjysy;
	}
	public void setQytd_jdjysy(List<TripContent> qytd_jdjysy)
	{
		this.qytd_jdjysy = qytd_jdjysy;
	}
	public List<TripContent> getQytd_jdjysz()
	{
		return qytd_jdjysz;
	}
	public void setQytd_jdjysz(List<TripContent> qytd_jdjysz)
	{
		this.qytd_jdjysz = qytd_jdjysz;
	}
	public List<TripContent> getQytd_jdjyx()
	{
		return qytd_jdjyx;
	}
	public void setQytd_jdjyx(List<TripContent> qytd_jdjyx)
	{
		this.qytd_jdjyx = qytd_jdjyx;
	}
	public List<TripContent> getQytd_jdjzs()
	{
		return qytd_jdjzs;
	}
	public void setQytd_jdjzs(List<TripContent> qytd_jdjzs)
	{
		this.qytd_jdjzs = qytd_jdjzs;
	}
	public List<TripContent> getQytd_jdjzx()
	{
		return qytd_jdjzx;
	}
	public void setQytd_jdjzx(List<TripContent> qytd_jdjzx)
	{
		this.qytd_jdjzx = qytd_jdjzx;
	}
	public List<TripContent> getQytd_qyrxs()
	{
		return qytd_qyrxs;
	}
	public void setQytd_qyrxs(List<TripContent> qytd_qyrxs)
	{
		this.qytd_qyrxs = qytd_qyrxs;
	}
	public List<TripContent> getSzsy_cjtj()
	{
		return szsy_cjtj;
	}

	public void setSzsy_cjtj(List<TripContent> szsy_cjtj)
	{
		this.szsy_cjtj = szsy_cjtj;
	}

	public List<TripContent> getSzsy_cjyt()
	{
		return szsy_cjyt;
	}

	public void setSzsy_cjyt(List<TripContent> szsy_cjyt)
	{
		this.szsy_cjyt = szsy_cjyt;
	}

	public List<TripContent> getSzsy_cjyyxy()
	{
		return szsy_cjyyxy;
	}

	public void setSzsy_cjyyxy(List<TripContent> szsy_cjyyxy)
	{
		this.szsy_cjyyxy = szsy_cjyyxy;
	}

	public List<TripContent> getSzsy_cjyyxz()
	{
		return szsy_cjyyxz;
	}

	public void setSzsy_cjyyxz(List<TripContent> szsy_cjyyxz)
	{
		this.szsy_cjyyxz = szsy_cjyyxz;
	}

	public List<TripContent> getSzsy_cjyyz()
	{
		return szsy_cjyyz;
	}

	public void setSzsy_cjyyz(List<TripContent> szsy_cjyyz)
	{
		this.szsy_cjyyz = szsy_cjyyz;
	}

	public List<TripContent> getSzsy_cjyz()
	{
		return szsy_cjyz;
	}

	public void setSzsy_cjyz(List<TripContent> szsy_cjyz)
	{
		this.szsy_cjyz = szsy_cjyz;
	}

	public List<TripContent> getSzsy_ggl()
	{
		return szsy_ggl;
	}

	public void setSzsy_ggl(List<TripContent> szsy_ggl)
	{
		this.szsy_ggl = szsy_ggl;
	}

	public List<TripContent> getSzsy_gncxt()
	{
		return szsy_gncxt;
	}

	public void setSzsy_gncxt(List<TripContent> szsy_gncxt)
	{
		this.szsy_gncxt = szsy_gncxt;
	}

	public List<TripContent> getSzsy_gncxyxy()
	{
		return szsy_gncxyxy;
	}

	public void setSzsy_gncxyxy(List<TripContent> szsy_gncxyxy)
	{
		this.szsy_gncxyxy = szsy_gncxyxy;
	}

	public List<TripContent> getSzsy_gncxyxz()
	{
		return szsy_gncxyxz;
	}

	public void setSzsy_gncxyxz(List<TripContent> szsy_gncxyxz)
	{
		this.szsy_gncxyxz = szsy_gncxyxz;
	}

	public List<TripContent> getSzsy_gncxyz()
	{
		return szsy_gncxyz;
	}

	public void setSzsy_gncxyz(List<TripContent> szsy_gncxyz)
	{
		this.szsy_gncxyz = szsy_gncxyz;
	}

	public List<TripContent> getSzsy_gncxz()
	{
		return szsy_gncxz;
	}

	public void setSzsy_gncxz(List<TripContent> szsy_gncxz)
	{
		this.szsy_gncxz = szsy_gncxz;
	}

	public List<TripContent> getSzsy_gntj()
	{
		return szsy_gntj;
	}

	public void setSzsy_gntj(List<TripContent> szsy_gntj)
	{
		this.szsy_gntj = szsy_gntj;
	}

	public List<TripContent> getSzsy_rdcs()
	{
		return szsy_rdcs;
	}

	public void setSzsy_rdcs(List<TripContent> szsy_rdcs)
	{
		this.szsy_rdcs = szsy_rdcs;
	}

	public List<TripContent> getSzsy_rdcsdb()
	{
		return szsy_rdcsdb;
	}

	public void setSzsy_rdcsdb(List<TripContent> szsy_rdcsdb)
	{
		this.szsy_rdcsdb = szsy_rdcsdb;
	}

	public List<TripContent> getSzsy_rqjd()
	{
		return szsy_rqjd;
	}

	public void setSzsy_rqjd(List<TripContent> szsy_rqjd)
	{
		this.szsy_rqjd = szsy_rqjd;
	}

	public List<TripContent> getSzsy_rqjddb()
	{
		return szsy_rqjddb;
	}

	public void setSzsy_rqjddb(List<TripContent> szsy_rqjddb)
	{
		this.szsy_rqjddb = szsy_rqjddb;
	}

	public List<TripContent> getSzsy_zbdxt()
	{
		return szsy_zbdxt;
	}

	public void setSzsy_zbdxt(List<TripContent> szsy_zbdxt)
	{
		this.szsy_zbdxt = szsy_zbdxt;
	}

	public List<TripContent> getSzsy_zbdxyxy()
	{
		return szsy_zbdxyxy;
	}

	public void setSzsy_zbdxyxy(List<TripContent> szsy_zbdxyxy)
	{
		this.szsy_zbdxyxy = szsy_zbdxyxy;
	}

	public List<TripContent> getSzsy_zbdxyxz()
	{
		return szsy_zbdxyxz;
	}

	public void setSzsy_zbdxyxz(List<TripContent> szsy_zbdxyxz)
	{
		this.szsy_zbdxyxz = szsy_zbdxyxz;
	}

	public List<TripContent> getSzsy_zbdxyz()
	{
		return szsy_zbdxyz;
	}

	public void setSzsy_zbdxyz(List<TripContent> szsy_zbdxyz)
	{
		this.szsy_zbdxyz = szsy_zbdxyz;
	}

	public List<TripContent> getSzsy_zbdxz()
	{
		return szsy_zbdxz;
	}

	public void setSzsy_zbdxz(List<TripContent> szsy_zbdxz)
	{
		this.szsy_zbdxz = szsy_zbdxz;
	}

	public List<TripContent> getSzsy_zbtj()
	{
		return szsy_zbtj;
	}

	public void setSzsy_zbtj(List<TripContent> szsy_zbtj)
	{
		this.szsy_zbtj = szsy_zbtj;
	}

	public List<TripContent> getSzsy_zyxtj()
	{
		return szsy_zyxtj;
	}

	public void setSzsy_zyxtj(List<TripContent> szsy_zyxtj)
	{
		this.szsy_zyxtj = szsy_zyxtj;
	}

	public ITripContentService getTripContentService()
	{
		return tripContentService;
	}

	public void setTripContentService(ITripContentService tripContentService)
	{
		this.tripContentService = tripContentService;
	}
	public List<TripContent> getZyx_cjbbys()
	{
		return zyx_cjbbys;
	}
	public void setZyx_cjbbys(List<TripContent> zyx_cjbbys)
	{
		this.zyx_cjbbys = zyx_cjbbys;
	}
	public List<TripContent> getZyx_cjbbyxy()
	{
		return zyx_cjbbyxy;
	}
	public void setZyx_cjbbyxy(List<TripContent> zyx_cjbbyxy)
	{
		this.zyx_cjbbyxy = zyx_cjbbyxy;
	}
	public List<TripContent> getZyx_cjbbyxz()
	{
		return zyx_cjbbyxz;
	}
	public void setZyx_cjbbyxz(List<TripContent> zyx_cjbbyxz)
	{
		this.zyx_cjbbyxz = zyx_cjbbyxz;
	}
	public List<TripContent> getZyx_cjbbz()
	{
		return zyx_cjbbz;
	}
	public void setZyx_cjbbz(List<TripContent> zyx_cjbbz)
	{
		this.zyx_cjbbz = zyx_cjbbz;
	}
	public List<TripContent> getZyx_gncyy()
	{
		return zyx_gncyy;
	}
	public void setZyx_gncyy(List<TripContent> zyx_gncyy)
	{
		this.zyx_gncyy = zyx_gncyy;
	}
	public List<TripContent> getZyx_gncyz()
	{
		return zyx_gncyz;
	}
	public void setZyx_gncyz(List<TripContent> zyx_gncyz)
	{
		this.zyx_gncyz = zyx_gncyz;
	}
	public List<TripContent> getZyx_gncyzh()
	{
		return zyx_gncyzh;
	}
	public void setZyx_gncyzh(List<TripContent> zyx_gncyzh)
	{
		this.zyx_gncyzh = zyx_gncyzh;
	}

	public List<TripContent> getZyx_jcqs()
	{
		return zyx_jcqs;
	}
	public void setZyx_jcqs(List<TripContent> zyx_jcqs)
	{
		this.zyx_jcqs = zyx_jcqs;
	}
	public List<TripContent> getZyx_jcqt()
	{
		return zyx_jcqt;
	}
	public void setZyx_jcqt(List<TripContent> zyx_jcqt)
	{
		this.zyx_jcqt = zyx_jcqt;
	}
	public List<TripContent> getZyx_jcqx()
	{
		return zyx_jcqx;
	}
	public void setZyx_jcqx(List<TripContent> zyx_jcqx)
	{
		this.zyx_jcqx = zyx_jcqx;
	}
	public List<TripContent> getZyx_jcqz()
	{
		return zyx_jcqz;
	}
	public void setZyx_jcqz(List<TripContent> zyx_jcqz)
	{
		this.zyx_jcqz = zyx_jcqz;
	}
	public List<TripContent> getZyx_lxszy()
	{
		return zyx_lxszy;
	}
	public void setZyx_lxszy(List<TripContent> zyx_lxszy)
	{
		this.zyx_lxszy = zyx_lxszy;
	}
	public List<TripContent> getZyx_lxszzs()
	{
		return zyx_lxszzs;
	}
	public void setZyx_lxszzs(List<TripContent> zyx_lxszzs)
	{
		this.zyx_lxszzs = zyx_lxszzs;
	}
	public List<TripContent> getZyx_lxszzx()
	{
		return zyx_lxszzx;
	}
	public void setZyx_lxszzx(List<TripContent> zyx_lxszzx)
	{
		this.zyx_lxszzx = zyx_lxszzx;
	}
	public List<TripContent> getZyx_rdtj()
	{
		return zyx_rdtj;
	}
	public void setZyx_rdtj(List<TripContent> zyx_rdtj)
	{
		this.zyx_rdtj = zyx_rdtj;
	}
	public List<TripContent> getZyx_rdtj2y()
	{
		return zyx_rdtj2y;
	}
	public void setZyx_rdtj2y(List<TripContent> zyx_rdtj2y)
	{
		this.zyx_rdtj2y = zyx_rdtj2y;
	}
	public List<TripContent> getZyx_rdtj2zs()
	{
		return zyx_rdtj2zs;
	}
	public void setZyx_rdtj2zs(List<TripContent> zyx_rdtj2zs)
	{
		this.zyx_rdtj2zs = zyx_rdtj2zs;
	}
	public List<TripContent> getZyx_rdtj2zx()
	{
		return zyx_rdtj2zx;
	}
	public void setZyx_rdtj2zx(List<TripContent> zyx_rdtj2zx)
	{
		this.zyx_rdtj2zx = zyx_rdtj2zx;
	}
	public List<TripContent> getZyx_rqjd()
	{
		return zyx_rqjd;
	}
	public void setZyx_rqjd(List<TripContent> zyx_rqjd)
	{
		this.zyx_rqjd = zyx_rqjd;
	}
	public List<TripContent> getZyx_tw()
	{
		return zyx_tw;
	}
	public void setZyx_tw(List<TripContent> zyx_tw)
	{
		this.zyx_tw = zyx_tw;
	}
	public List<TripContent> getZyx_yjgl()
	{
		return zyx_yjgl;
	}
	public void setZyx_yjgl(List<TripContent> zyx_yjgl)
	{
		this.zyx_yjgl = zyx_yjgl;
	}
	public List<TripContent> getGny_djt()
	{
		return gny_djt;
	}
	public void setGny_djt(List<TripContent> gny_djt)
	{
		this.gny_djt = gny_djt;
	}
	public List<TripContent> getGny_qjqt()
	{
		return gny_qjqt;
	}
	public void setGny_qjqt(List<TripContent> gny_qjqt)
	{
		this.gny_qjqt = gny_qjqt;
	}
	public List<TripContent> getGny_qjqy()
	{
		return gny_qjqy;
	}
	public void setGny_qjqy(List<TripContent> gny_qjqy)
	{
		this.gny_qjqy = gny_qjqy;
	}
	public List<TripContent> getGny_qjqz()
	{
		return gny_qjqz;
	}
	public void setGny_qjqz(List<TripContent> gny_qjqz)
	{
		this.gny_qjqz = gny_qjqz;
	}
	public List<TripContent> getGny_qjqz2()
	{
		return gny_qjqz2;
	}
	public void setGny_qjqz2(List<TripContent> gny_qjqz2)
	{
		this.gny_qjqz2 = gny_qjqz2;
	}
	public List<TripContent> getGny_qjqzs()
	{
		return gny_qjqzs;
	}
	public void setGny_qjqzs(List<TripContent> gny_qjqzs)
	{
		this.gny_qjqzs = gny_qjqzs;
	}
	public List<TripContent> getGny_qjqzx()
	{
		return gny_qjqzx;
	}
	public void setGny_qjqzx(List<TripContent> gny_qjqzx)
	{
		this.gny_qjqzx = gny_qjqzx;
	}
	public List<TripContent> getGny_rq()
	{
		return gny_rq;
	}
	public void setGny_rq(List<TripContent> gny_rq)
	{
		this.gny_rq = gny_rq;
	}
	public List<TripContent> getGny_zxt()
	{
		return gny_zxt;
	}
	public void setGny_zxt(List<TripContent> gny_zxt)
	{
		this.gny_zxt = gny_zxt;
	}
	public List<TripContent> getGny_zxtu()
	{
		return gny_zxtu;
	}
	public void setGny_zxtu(List<TripContent> gny_zxtu)
	{
		this.gny_zxtu = gny_zxtu;
	}
	public List<TripContent> getGny_zxy()
	{
		return gny_zxy;
	}
	public void setGny_zxy(List<TripContent> gny_zxy)
	{
		this.gny_zxy = gny_zxy;
	}
	public List<TripContent> getGny_zxz()
	{
		return gny_zxz;
	}
	public void setGny_zxz(List<TripContent> gny_zxz)
	{
		this.gny_zxz = gny_zxz;
	}
	public List<TripContent> getGny_zxz2()
	{
		return gny_zxz2;
	}
	public void setGny_zxz2(List<TripContent> gny_zxz2)
	{
		this.gny_zxz2 = gny_zxz2;
	}
	public List<TripContent> getGny_zxzs()
	{
		return gny_zxzs;
	}
	public void setGny_zxzs(List<TripContent> gny_zxzs)
	{
		this.gny_zxzs = gny_zxzs;
	}
	public List<TripContent> getGny_zxzx()
	{
		return gny_zxzx;
	}
	public void setGny_zxzx(List<TripContent> gny_zxzx)
	{
		this.gny_zxzx = gny_zxzx;
	}
	public List<TripContent> getZyx_rmzyx() {
		return zyx_rmzyx;
	}
	public void setZyx_rmzyx(List<TripContent> zyx_rmzyx) {
		this.zyx_rmzyx = zyx_rmzyx;
	}
	public List<TripContent> getZyx_yx() {
		return zyx_yx;
	}
	public void setZyx_yx(List<TripContent> zyx_yx) {
		this.zyx_yx = zyx_yx;
	}
	public List<TripContent> getZb_eryjds()
	{
		return zb_eryjds;
	}
	public void setZb_eryjds(List<TripContent> zb_eryjds)
	{
		this.zb_eryjds = zb_eryjds;
	}
	public List<TripContent> getZb_eryjdx()
	{
		return zb_eryjdx;
	}
	public void setZb_eryjdx(List<TripContent> zb_eryjdx)
	{
		this.zb_eryjdx = zb_eryjdx;
	}
	public List<TripContent> getZb_fxb()
	{
		return zb_fxb;
	}
	public void setZb_fxb(List<TripContent> zb_fxb)
	{
		this.zb_fxb = zb_fxb;
	}
	public List<TripContent> getZb_gcmz()
	{
		return zb_gcmz;
	}
	public void setZb_gcmz(List<TripContent> zb_gcmz)
	{
		this.zb_gcmz = zb_gcmz;
	}
	public List<TripContent> getZb_hbwq()
	{
		return zb_hbwq;
	}
	public void setZb_hbwq(List<TripContent> zb_hbwq)
	{
		this.zb_hbwq = zb_hbwq;
	}
	public List<TripContent> getZb_jctj()
	{
		return zb_jctj;
	}
	public void setZb_jctj(List<TripContent> zb_jctj)
	{
		this.zb_jctj = zb_jctj;
	}
	public List<TripContent> getZb_jjs()
	{
		return zb_jjs;
	}
	public void setZb_jjs(List<TripContent> zb_jjs)
	{
		this.zb_jjs = zb_jjs;
	}
	public List<TripContent> getZb_jjx()
	{
		return zb_jjx;
	}
	public void setZb_jjx(List<TripContent> zb_jjx)
	{
		this.zb_jjx = zb_jjx;
	}
	public List<TripContent> getZb_jjz()
	{
		return zb_jjz;
	}
	public void setZb_jjz(List<TripContent> zb_jjz)
	{
		this.zb_jjz = zb_jjz;
	}
	public List<TripContent> getZb_jqph()
	{
		return zb_jqph;
	}
	public void setZb_jqph(List<TripContent> zb_jqph)
	{
		this.zb_jqph = zb_jqph;
	}
	public List<TripContent> getZb_qytzsys()
	{
		return zb_qytzsys;
	}
	public void setZb_qytzsys(List<TripContent> zb_qytzsys)
	{
		this.zb_qytzsys = zb_qytzsys;
	}
	public List<TripContent> getZb_qytzsyx()
	{
		return zb_qytzsyx;
	}
	public void setZb_qytzsyx(List<TripContent> zb_qytzsyx)
	{
		this.zb_qytzsyx = zb_qytzsyx;
	}
	public List<TripContent> getZb_qytzsz()
	{
		return zb_qytzsz;
	}
	public void setZb_qytzsz(List<TripContent> zb_qytzsz)
	{
		this.zb_qytzsz = zb_qytzsz;
	}
	public List<TripContent> getZb_qytzx()
	{
		return zb_qytzx;
	}
	public void setZb_qytzx(List<TripContent> zb_qytzx)
	{
		this.zb_qytzx = zb_qytzx;
	}
	public List<TripContent> getZb_rqcs()
	{
		return zb_rqcs;
	}
	public void setZb_rqcs(List<TripContent> zb_rqcs)
	{
		this.zb_rqcs = zb_rqcs;
	}
	public List<TripContent> getZb_sryjds()
	{
		return zb_sryjds;
	}
	public void setZb_sryjds(List<TripContent> zb_sryjds)
	{
		this.zb_sryjds = zb_sryjds;
	}
	public List<TripContent> getZb_sryjdx()
	{
		return zb_sryjdx;
	}
	public void setZb_sryjdx(List<TripContent> zb_sryjdx)
	{
		this.zb_sryjdx = zb_sryjdx;
	}
	public List<TripContent> getZb_szywsys()
	{
		return zb_szywsys;
	}
	public void setZb_szywsys(List<TripContent> zb_szywsys)
	{
		this.zb_szywsys = zb_szywsys;
	}
	public List<TripContent> getZb_szywsyx()
	{
		return zb_szywsyx;
	}
	public void setZb_szywsyx(List<TripContent> zb_szywsyx)
	{
		this.zb_szywsyx = zb_szywsyx;
	}
	public List<TripContent> getZb_szywsz()
	{
		return zb_szywsz;
	}
	public void setZb_szywsz(List<TripContent> zb_szywsz)
	{
		this.zb_szywsz = zb_szywsz;
	}
	public List<TripContent> getZb_szywx()
	{
		return zb_szywx;
	}
	public void setZb_szywx(List<TripContent> zb_szywx)
	{
		this.zb_szywx = zb_szywx;
	}
	public List<TripContent> getZb_yryjds()
	{
		return zb_yryjds;
	}
	public void setZb_yryjds(List<TripContent> zb_yryjds)
	{
		this.zb_yryjds = zb_yryjds;
	}
	public List<TripContent> getZb_ysly()
	{
		return zb_ysly;
	}
	public void setZb_ysly(List<TripContent> zb_ysly)
	{
		this.zb_ysly = zb_ysly;
	}
	public List<TripContent> getZb_zttjsys()
	{
		return zb_zttjsys;
	}
	public void setZb_zttjsys(List<TripContent> zb_zttjsys)
	{
		this.zb_zttjsys = zb_zttjsys;
	}
	public List<TripContent> getZb_zttjsyx()
	{
		return zb_zttjsyx;
	}
	public void setZb_zttjsyx(List<TripContent> zb_zttjsyx)
	{
		this.zb_zttjsyx = zb_zttjsyx;
	}
	public List<TripContent> getZb_zttjsz()
	{
		return zb_zttjsz;
	}
	public void setZb_zttjsz(List<TripContent> zb_zttjsz)
	{
		this.zb_zttjsz = zb_zttjsz;
	}
	public List<TripContent> getZb_zttjx()
	{
		return zb_zttjx;
	}
	public void setZb_zttjx(List<TripContent> zb_zttjx)
	{
		this.zb_zttjx = zb_zttjx;
	}
	public List<TripContent> getCjy_cjys()
	{
		return cjy_cjys;
	}
	public void setCjy_cjys(List<TripContent> cjy_cjys)
	{
		this.cjy_cjys = cjy_cjys;
	}
	public List<TripContent> getCjy_cjyt()
	{
		return cjy_cjyt;
	}
	public void setCjy_cjyt(List<TripContent> cjy_cjyt)
	{
		this.cjy_cjyt = cjy_cjyt;
	}
	public List<TripContent> getCjy_cjyx()
	{
		return cjy_cjyx;
	}
	public void setCjy_cjyx(List<TripContent> cjy_cjyx)
	{
		this.cjy_cjyx = cjy_cjyx;
	}
	public List<TripContent> getCjy_cjyz()
	{
		return cjy_cjyz;
	}
	public void setCjy_cjyz(List<TripContent> cjy_cjyz)
	{
		this.cjy_cjyz = cjy_cjyz;
	}
	public List<TripContent> getCjy_cyozs()
	{
		return cjy_cyozs;
	}
	public void setCjy_cyozs(List<TripContent> cjy_cyozs)
	{
		this.cjy_cyozs = cjy_cyozs;
	}
	public List<TripContent> getCjy_cyozx()
	{
		return cjy_cyozx;
	}
	public void setCjy_cyozx(List<TripContent> cjy_cyozx)
	{
		this.cjy_cyozx = cjy_cyozx;
	}
	public List<TripContent> getCjy_dj1ys()
	{
		return cjy_dj1ys;
	}
	public void setCjy_dj1ys(List<TripContent> cjy_dj1ys)
	{
		this.cjy_dj1ys = cjy_dj1ys;
	}
	public List<TripContent> getCjy_dj1yx()
	{
		return cjy_dj1yx;
	}
	public void setCjy_dj1yx(List<TripContent> cjy_dj1yx)
	{
		this.cjy_dj1yx = cjy_dj1yx;
	}
	public List<TripContent> getCjy_dj1z()
	{
		return cjy_dj1z;
	}
	public void setCjy_dj1z(List<TripContent> cjy_dj1z)
	{
		this.cjy_dj1z = cjy_dj1z;
	}
	public List<TripContent> getCjy_dj2ys()
	{
		return cjy_dj2ys;
	}
	public void setCjy_dj2ys(List<TripContent> cjy_dj2ys)
	{
		this.cjy_dj2ys = cjy_dj2ys;
	}
	public List<TripContent> getCjy_dj2yx()
	{
		return cjy_dj2yx;
	}
	public void setCjy_dj2yx(List<TripContent> cjy_dj2yx)
	{
		this.cjy_dj2yx = cjy_dj2yx;
	}
	public List<TripContent> getCjy_dj2z()
	{
		return cjy_dj2z;
	}
	public void setCjy_dj2z(List<TripContent> cjy_dj2z)
	{
		this.cjy_dj2z = cjy_dj2z;
	}
	public List<TripContent> getCjy_dj3ys()
	{
		return cjy_dj3ys;
	}
	public void setCjy_dj3ys(List<TripContent> cjy_dj3ys)
	{
		this.cjy_dj3ys = cjy_dj3ys;
	}
	public List<TripContent> getCjy_dj3yx()
	{
		return cjy_dj3yx;
	}
	public void setCjy_dj3yx(List<TripContent> cjy_dj3yx)
	{
		this.cjy_dj3yx = cjy_dj3yx;
	}
	public List<TripContent> getCjy_dj3z()
	{
		return cjy_dj3z;
	}
	public void setCjy_dj3z(List<TripContent> cjy_dj3z)
	{
		this.cjy_dj3z = cjy_dj3z;
	}
	public List<TripContent> getCjy_dj4ys()
	{
		return cjy_dj4ys;
	}
	public void setCjy_dj4ys(List<TripContent> cjy_dj4ys)
	{
		this.cjy_dj4ys = cjy_dj4ys;
	}
	public List<TripContent> getCjy_dj4yx()
	{
		return cjy_dj4yx;
	}
	public void setCjy_dj4yx(List<TripContent> cjy_dj4yx)
	{
		this.cjy_dj4yx = cjy_dj4yx;
	}
	public List<TripContent> getCjy_dj4z()
	{
		return cjy_dj4z;
	}
	public void setCjy_dj4z(List<TripContent> cjy_dj4z)
	{
		this.cjy_dj4z = cjy_dj4z;
	}
	public List<TripContent> getCjy_dybb()
	{
		return cjy_dybb;
	}
	public void setCjy_dybb(List<TripContent> cjy_dybb)
	{
		this.cjy_dybb = cjy_dybb;
	}
	public List<TripContent> getCjy_gats()
	{
		return cjy_gats;
	}
	public void setCjy_gats(List<TripContent> cjy_gats)
	{
		this.cjy_gats = cjy_gats;
	}
	public List<TripContent> getCjy_gatx()
	{
		return cjy_gatx;
	}
	public void setCjy_gatx(List<TripContent> cjy_gatx)
	{
		this.cjy_gatx = cjy_gatx;
	}
	public List<TripContent> getCjy_hwt()
	{
		return cjy_hwt;
	}
	public void setCjy_hwt(List<TripContent> cjy_hwt)
	{
		this.cjy_hwt = cjy_hwt;
	}
	public List<TripContent> getCjy_hwx()
	{
		return cjy_hwx;
	}
	public void setCjy_hwx(List<TripContent> cjy_hwx)
	{
		this.cjy_hwx = cjy_hwx;
	}
	public List<TripContent> getCjy_hwy1()
	{
		return cjy_hwy1;
	}
	public void setCjy_hwy1(List<TripContent> cjy_hwy1)
	{
		this.cjy_hwy1 = cjy_hwy1;
	}
	public List<TripContent> getCjy_hwy2()
	{
		return cjy_hwy2;
	}
	public void setCjy_hwy2(List<TripContent> cjy_hwy2)
	{
		this.cjy_hwy2 = cjy_hwy2;
	}
	public List<TripContent> getCjy_hwy3()
	{
		return cjy_hwy3;
	}
	public void setCjy_hwy3(List<TripContent> cjy_hwy3)
	{
		this.cjy_hwy3 = cjy_hwy3;
	}
	public List<TripContent> getCjy_hwy4()
	{
		return cjy_hwy4;
	}
	public void setCjy_hwy4(List<TripContent> cjy_hwy4)
	{
		this.cjy_hwy4 = cjy_hwy4;
	}
	public List<TripContent> getCjy_hwz()
	{
		return cjy_hwz;
	}
	public void setCjy_hwz(List<TripContent> cjy_hwz)
	{
		this.cjy_hwz = cjy_hwz;
	}
	public List<TripContent> getCjy_jctj()
	{
		return cjy_jctj;
	}
	public void setCjy_jctj(List<TripContent> cjy_jctj)
	{
		this.cjy_jctj = cjy_jctj;
	}
	public List<TripContent> getCjy_jdtj()
	{
		return cjy_jdtj;
	}
	public void setCjy_jdtj(List<TripContent> cjy_jdtj)
	{
		this.cjy_jdtj = cjy_jdtj;
	}
	public List<TripContent> getCjy_rmqz()
	{
		return cjy_rmqz;
	}
	public void setCjy_rmqz(List<TripContent> cjy_rmqz)
	{
		this.cjy_rmqz = cjy_rmqz;
	}
	public List<TripContent> getCjy_zj()
	{
		return cjy_zj;
	}
	public void setCjy_zj(List<TripContent> cjy_zj)
	{
		this.cjy_zj = cjy_zj;
	}
	public List<TripContent> getZb_ysws() {
		return zb_ysws;
	}
	public void setZb_ysws(List<TripContent> zb_ysws) {
		this.zb_ysws = zb_ysws;
	}
	public List<TripContent> getZb_eryjdz() {
		return zb_eryjdz;
	}
	public void setZb_eryjdz(List<TripContent> zb_eryjdz) {
		this.zb_eryjdz = zb_eryjdz;
	}
	public List<TripContent> getZb_sryjdz() {
		return zb_sryjdz;
	}
	public void setZb_sryjdz(List<TripContent> zb_sryjdz) {
		this.zb_sryjdz = zb_sryjdz;
	}
	public List<TripContent> getZb_yryjdx() {
		return zb_yryjdx;
	}
	public void setZb_yryjdx(List<TripContent> zb_yryjdx) {
		this.zb_yryjdx = zb_yryjdx;
	}
	public List<TripContent> getZb_yryjdz() {
		return zb_yryjdz;
	}
	public void setZb_yryjdz(List<TripContent> zb_yryjdz) {
		this.zb_yryjdz = zb_yryjdz;
	}
	public List<TripContent> getCjy_djt() {
		return cjy_djt;
	}
	public void setCjy_djt(List<TripContent> cjy_djt) {
		this.cjy_djt = cjy_djt;
	}
	public List<TripContent> getCjy_cyozz() {
		return cjy_cyozz;
	}
	public void setCjy_cyozz(List<TripContent> cjy_cyozz) {
		this.cjy_cyozz = cjy_cyozz;
	}
	public List<TripContent> getQytd_hydd() {
		return qytd_hydd;
	}
	public void setQytd_hydd(List<TripContent> qytd_hydd) {
		this.qytd_hydd = qytd_hydd;
	}
	public List<TripContent> getQytd_qyfwy() {
		return qytd_qyfwy;
	}
	public void setQytd_qyfwy(List<TripContent> qytd_qyfwy) {
		this.qytd_qyfwy = qytd_qyfwy;
	}
	public List<TripContent> getQytd_qyfwz() {
		return qytd_qyfwz;
	}
	public void setQytd_qyfwz(List<TripContent> qytd_qyfwz) {
		this.qytd_qyfwz = qytd_qyfwz;
	}
	public List<TripContent> getQytd_qyfwzh() {
		return qytd_qyfwzh;
	}
	public void setQytd_qyfwzh(List<TripContent> qytd_qyfwzh) {
		this.qytd_qyfwzh = qytd_qyfwzh;
	}
	public List<TripContent> getQytd_qyrxx() {
		return qytd_qyrxx;
	}
	public void setQytd_qyrxx(List<TripContent> qytd_qyrxx) {
		this.qytd_qyrxx = qytd_qyrxx;
	}
	public List<TripContent> getQytd_rdjq() {
		return qytd_rdjq;
	}
	public void setQytd_rdjq(List<TripContent> qytd_rdjq) {
		this.qytd_rdjq = qytd_rdjq;
	}
	public List<TripContent> getQytd_ywgl() {
		return qytd_ywgl;
	}
	public void setQytd_ywgl(List<TripContent> qytd_ywgl) {
		this.qytd_ywgl = qytd_ywgl;
	}
	public List<TripContent> getCsjd_banner()
	{
		return csjd_banner;
	}
	public void setCsjd_banner(List<TripContent> csjd_banner)
	{
		this.csjd_banner = csjd_banner;
	}
	public List<TripContent> getCsjd_fqt()
	{
		return csjd_fqt;
	}
	public void setCsjd_fqt(List<TripContent> csjd_fqt)
	{
		this.csjd_fqt = csjd_fqt;
	}
	public List<TripContent> getCsjd_fqy()
	{
		return csjd_fqy;
	}
	public void setCsjd_fqy(List<TripContent> csjd_fqy)
	{
		this.csjd_fqy = csjd_fqy;
	}
	public List<TripContent> getCsjd_fqz()
	{
		return csjd_fqz;
	}
	public void setCsjd_fqz(List<TripContent> csjd_fqz)
	{
		this.csjd_fqz = csjd_fqz;
	}
	public List<TripContent> getCsjd_fqzs()
	{
		return csjd_fqzs;
	}
	public void setCsjd_fqzs(List<TripContent> csjd_fqzs)
	{
		this.csjd_fqzs = csjd_fqzs;
	}
	public List<TripContent> getCsjd_fqzx()
	{
		return csjd_fqzx;
	}
	public void setCsjd_fqzx(List<TripContent> csjd_fqzx)
	{
		this.csjd_fqzx = csjd_fqzx;
	}
	public List<TripContent> getCsjd_gjt()
	{
		return csjd_gjt;
	}
	public void setCsjd_gjt(List<TripContent> csjd_gjt)
	{
		this.csjd_gjt = csjd_gjt;
	}
	public List<TripContent> getCsjd_gjy()
	{
		return csjd_gjy;
	}
	public void setCsjd_gjy(List<TripContent> csjd_gjy)
	{
		this.csjd_gjy = csjd_gjy;
	}
	public List<TripContent> getCsjd_gjz()
	{
		return csjd_gjz;
	}
	public void setCsjd_gjz(List<TripContent> csjd_gjz)
	{
		this.csjd_gjz = csjd_gjz;
	}
	public List<TripContent> getCsjd_gjzs()
	{
		return csjd_gjzs;
	}
	public void setCsjd_gjzs(List<TripContent> csjd_gjzs)
	{
		this.csjd_gjzs = csjd_gjzs;
	}
	public List<TripContent> getCsjd_gjzx()
	{
		return csjd_gjzx;
	}
	public void setCsjd_gjzx(List<TripContent> csjd_gjzx)
	{
		this.csjd_gjzx = csjd_gjzx;
	}
	public List<TripContent> getCsjd_gzt()
	{
		return csjd_gzt;
	}
	public void setCsjd_gzt(List<TripContent> csjd_gzt)
	{
		this.csjd_gzt = csjd_gzt;
	}
	public List<TripContent> getCsjd_gzys()
	{
		return csjd_gzys;
	}
	public void setCsjd_gzys(List<TripContent> csjd_gzys)
	{
		this.csjd_gzys = csjd_gzys;
	}
	public List<TripContent> getCsjd_gzyx()
	{
		return csjd_gzyx;
	}
	public void setCsjd_gzyx(List<TripContent> csjd_gzyx)
	{
		this.csjd_gzyx = csjd_gzyx;
	}
	public List<TripContent> getCsjd_gzz()
	{
		return csjd_gzz;
	}
	public void setCsjd_gzz(List<TripContent> csjd_gzz)
	{
		this.csjd_gzz = csjd_gzz;
	}
	public List<TripContent> getCsjd_gzzs()
	{
		return csjd_gzzs;
	}
	public void setCsjd_gzzs(List<TripContent> csjd_gzzs)
	{
		this.csjd_gzzs = csjd_gzzs;
	}
	public List<TripContent> getCsjd_gzzx()
	{
		return csjd_gzzx;
	}
	public void setCsjd_gzzx(List<TripContent> csjd_gzzx)
	{
		this.csjd_gzzx = csjd_gzzx;
	}
	public List<TripContent> getCsjd_gzzzy()
	{
		return csjd_gzzzy;
	}
	public void setCsjd_gzzzy(List<TripContent> csjd_gzzzy)
	{
		this.csjd_gzzzy = csjd_gzzzy;
	}
	public List<TripContent> getCsjd_gzzzz()
	{
		return csjd_gzzzz;
	}
	public void setCsjd_gzzzz(List<TripContent> csjd_gzzzz)
	{
		this.csjd_gzzzz = csjd_gzzzz;
	}
	public List<TripContent> getCsjd_hdt()
	{
		return csjd_hdt;
	}
	public void setCsjd_hdt(List<TripContent> csjd_hdt)
	{
		this.csjd_hdt = csjd_hdt;
	}
	public List<TripContent> getCsjd_hdys()
	{
		return csjd_hdys;
	}
	public void setCsjd_hdys(List<TripContent> csjd_hdys)
	{
		this.csjd_hdys = csjd_hdys;
	}
	public List<TripContent> getCsjd_hdyx()
	{
		return csjd_hdyx;
	}
	public void setCsjd_hdyx(List<TripContent> csjd_hdyx)
	{
		this.csjd_hdyx = csjd_hdyx;
	}
	public List<TripContent> getCsjd_hdz()
	{
		return csjd_hdz;
	}
	public void setCsjd_hdz(List<TripContent> csjd_hdz)
	{
		this.csjd_hdz = csjd_hdz;
	}
	public List<TripContent> getCsjd_hdzs()
	{
		return csjd_hdzs;
	}
	public void setCsjd_hdzs(List<TripContent> csjd_hdzs)
	{
		this.csjd_hdzs = csjd_hdzs;
	}
	public List<TripContent> getCsjd_hdzx()
	{
		return csjd_hdzx;
	}
	public void setCsjd_hdzx(List<TripContent> csjd_hdzx)
	{
		this.csjd_hdzx = csjd_hdzx;
	}
	public List<TripContent> getCsjd_hdzz()
	{
		return csjd_hdzz;
	}
	public void setCsjd_hdzz(List<TripContent> csjd_hdzz)
	{
		this.csjd_hdzz = csjd_hdzz;
	}
	public List<TripContent> getCsjd_myt()
	{
		return csjd_myt;
	}
	public void setCsjd_myt(List<TripContent> csjd_myt)
	{
		this.csjd_myt = csjd_myt;
	}
	public List<TripContent> getCsjd_myys()
	{
		return csjd_myys;
	}
	public void setCsjd_myys(List<TripContent> csjd_myys)
	{
		this.csjd_myys = csjd_myys;
	}
	public List<TripContent> getCsjd_myyx()
	{
		return csjd_myyx;
	}
	public void setCsjd_myyx(List<TripContent> csjd_myyx)
	{
		this.csjd_myyx = csjd_myyx;
	}
	public List<TripContent> getCsjd_myz()
	{
		return csjd_myz;
	}
	public void setCsjd_myz(List<TripContent> csjd_myz)
	{
		this.csjd_myz = csjd_myz;
	}
	public List<TripContent> getCsjd_myzs()
	{
		return csjd_myzs;
	}
	public void setCsjd_myzs(List<TripContent> csjd_myzs)
	{
		this.csjd_myzs = csjd_myzs;
	}
	public List<TripContent> getCsjd_myzx()
	{
		return csjd_myzx;
	}
	public void setCsjd_myzx(List<TripContent> csjd_myzx)
	{
		this.csjd_myzx = csjd_myzx;
	}
	public List<TripContent> getCsjd_qzt()
	{
		return csjd_qzt;
	}
	public void setCsjd_qzt(List<TripContent> csjd_qzt)
	{
		this.csjd_qzt = csjd_qzt;
	}
	public List<TripContent> getCsjd_qzy()
	{
		return csjd_qzy;
	}
	public void setCsjd_qzy(List<TripContent> csjd_qzy)
	{
		this.csjd_qzy = csjd_qzy;
	}
	public List<TripContent> getCsjd_qzz()
	{
		return csjd_qzz;
	}
	public void setCsjd_qzz(List<TripContent> csjd_qzz)
	{
		this.csjd_qzz = csjd_qzz;
	}
	public List<TripContent> getCsjd_qzzs()
	{
		return csjd_qzzs;
	}
	public void setCsjd_qzzs(List<TripContent> csjd_qzzs)
	{
		this.csjd_qzzs = csjd_qzzs;
	}
	public List<TripContent> getCsjd_qzzx()
	{
		return csjd_qzzx;
	}
	public void setCsjd_qzzx(List<TripContent> csjd_qzzx)
	{
		this.csjd_qzzx = csjd_qzzx;
	}
	public List<TripContent> getCsjd_txt()
	{
		return csjd_txt;
	}
	public void setCsjd_txt(List<TripContent> csjd_txt)
	{
		this.csjd_txt = csjd_txt;
	}
	public List<TripContent> getCsjd_txy()
	{
		return csjd_txy;
	}
	public void setCsjd_txy(List<TripContent> csjd_txy)
	{
		this.csjd_txy = csjd_txy;
	}
	public List<TripContent> getCsjd_txz()
	{
		return csjd_txz;
	}
	public void setCsjd_txz(List<TripContent> csjd_txz)
	{
		this.csjd_txz = csjd_txz;
	}
	public List<TripContent> getCsjd_txzs()
	{
		return csjd_txzs;
	}
	public void setCsjd_txzs(List<TripContent> csjd_txzs)
	{
		this.csjd_txzs = csjd_txzs;
	}
	public List<TripContent> getCsjd_txzx()
	{
		return csjd_txzx;
	}
	public void setCsjd_txzx(List<TripContent> csjd_txzx)
	{
		this.csjd_txzx = csjd_txzx;
	}
	public List<TripContent> getCsjd_tyt()
	{
		return csjd_tyt;
	}
	public void setCsjd_tyt(List<TripContent> csjd_tyt)
	{
		this.csjd_tyt = csjd_tyt;
	}
	public List<TripContent> getCsjd_tyy()
	{
		return csjd_tyy;
	}
	public void setCsjd_tyy(List<TripContent> csjd_tyy)
	{
		this.csjd_tyy = csjd_tyy;
	}
	public List<TripContent> getCsjd_tyz()
	{
		return csjd_tyz;
	}
	public void setCsjd_tyz(List<TripContent> csjd_tyz)
	{
		this.csjd_tyz = csjd_tyz;
	}
	public List<TripContent> getCsjd_tyzs()
	{
		return csjd_tyzs;
	}
	public void setCsjd_tyzs(List<TripContent> csjd_tyzs)
	{
		this.csjd_tyzs = csjd_tyzs;
	}
	public List<TripContent> getCsjd_tyzx()
	{
		return csjd_tyzx;
	}
	public void setCsjd_tyzx(List<TripContent> csjd_tyzx)
	{
		this.csjd_tyzx = csjd_tyzx;
	}
	public List<TripContent> getCsjd_wst()
	{
		return csjd_wst;
	}
	public void setCsjd_wst(List<TripContent> csjd_wst)
	{
		this.csjd_wst = csjd_wst;
	}
	public List<TripContent> getCsjd_wsys()
	{
		return csjd_wsys;
	}
	public void setCsjd_wsys(List<TripContent> csjd_wsys)
	{
		this.csjd_wsys = csjd_wsys;
	}
	public List<TripContent> getCsjd_wsyx()
	{
		return csjd_wsyx;
	}
	public void setCsjd_wsyx(List<TripContent> csjd_wsyx)
	{
		this.csjd_wsyx = csjd_wsyx;
	}
	public List<TripContent> getCsjd_wsz()
	{
		return csjd_wsz;
	}
	public void setCsjd_wsz(List<TripContent> csjd_wsz)
	{
		this.csjd_wsz = csjd_wsz;
	}
	public List<TripContent> getCsjd_wszs()
	{
		return csjd_wszs;
	}
	public void setCsjd_wszs(List<TripContent> csjd_wszs)
	{
		this.csjd_wszs = csjd_wszs;
	}
	public List<TripContent> getCsjd_wszx()
	{
		return csjd_wszx;
	}
	public void setCsjd_wszx(List<TripContent> csjd_wszx)
	{
		this.csjd_wszx = csjd_wszx;
	}
	public List<TripContent> getCsjd_wszz()
	{
		return csjd_wszz;
	}
	public void setCsjd_wszz(List<TripContent> csjd_wszz)
	{
		this.csjd_wszz = csjd_wszz;
	}
	public List<TripContent> getCsjd_yst()
	{
		return csjd_yst;
	}
	public void setCsjd_yst(List<TripContent> csjd_yst)
	{
		this.csjd_yst = csjd_yst;
	}
	public List<TripContent> getCsjd_ysys()
	{
		return csjd_ysys;
	}
	public void setCsjd_ysys(List<TripContent> csjd_ysys)
	{
		this.csjd_ysys = csjd_ysys;
	}
	public List<TripContent> getCsjd_ysyx()
	{
		return csjd_ysyx;
	}
	public void setCsjd_ysyx(List<TripContent> csjd_ysyx)
	{
		this.csjd_ysyx = csjd_ysyx;
	}
	public List<TripContent> getCsjd_ysz()
	{
		return csjd_ysz;
	}
	public void setCsjd_ysz(List<TripContent> csjd_ysz)
	{
		this.csjd_ysz = csjd_ysz;
	}
	public List<TripContent> getCsjd_yszs()
	{
		return csjd_yszs;
	}
	public void setCsjd_yszs(List<TripContent> csjd_yszs)
	{
		this.csjd_yszs = csjd_yszs;
	}
	public List<TripContent> getCsjd_yszx()
	{
		return csjd_yszx;
	}
	public void setCsjd_yszx(List<TripContent> csjd_yszx)
	{
		this.csjd_yszx = csjd_yszx;
	}
	public List<TripContent> getCsjd_yszzy()
	{
		return csjd_yszzy;
	}
	public void setCsjd_yszzy(List<TripContent> csjd_yszzy)
	{
		this.csjd_yszzy = csjd_yszzy;
	}
	public List<TripContent> getCsjd_yszzz()
	{
		return csjd_yszzz;
	}
	public void setCsjd_yszzz(List<TripContent> csjd_yszzz)
	{
		this.csjd_yszzz = csjd_yszzz;
	}
	public IAreaService getAreaService() {
		return areaService;
	}
	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}
	public List<Region> getBannerRegions() {
		return bannerRegions;
	}
	public void setBannerRegions(List<Region> bannerRegions) {
		this.bannerRegions = bannerRegions;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public List<Region> getRegions() {
		return regions;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	public IRegionService getRegionService() {
		return regionService;
	}
	public void setRegionService(IRegionService regionService) {
		this.regionService = regionService;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public List<ThemeFlash> getSy()
	{
		return sy;
	}
	public void setSy(List<ThemeFlash> sy)
	{
		this.sy = sy;
	}
	public IThemeFlashService getThemeFlashService()
	{
		return themeFlashService;
	}
	public void setThemeFlashService(IThemeFlashService themeFlashService)
	{
		this.themeFlashService = themeFlashService;
	}
	public List<ThemeFlash> getCj() {
		return cj;
	}
	public void setCj(List<ThemeFlash> cj) {
		this.cj = cj;
	}
	public List<ThemeFlash> getGn() {
		return gn;
	}
	public void setGn(List<ThemeFlash> gn) {
		this.gn = gn;
	}
	public List<ThemeFlash> getQytd() {
		return qytd;
	}
	public void setQytd(List<ThemeFlash> qytd) {
		this.qytd = qytd;
	}
	public List<ThemeFlash> getZb() {
		return zb;
	}
	public void setZb(List<ThemeFlash> zb) {
		this.zb = zb;
	}
	public List<ThemeFlash> getZyx() {
		return zyx;
	}
	public void setZyx(List<ThemeFlash> zyx) {
		this.zyx = zyx;
	}
	
	public int getBannerFlag()
	{
		return bannerFlag;
	}
	public void setBannerFlag(int bannerFlag)
	{
		this.bannerFlag = bannerFlag;
	}
	public List<TripContent> getGg_ss()
	{
		return gg_ss;
	}
	public void setGg_ss(List<TripContent> gg_ss)
	{
		this.gg_ss = gg_ss;
	}
	public List<String> getSearchList()
	{
		return searchList;
	}
	public void setSearchList(List<String> searchList)
	{
		this.searchList = searchList;
	}
	public WebSite getWebSite()
	{
		return webSite;
	}
	public void setWebSite(WebSite webSite)
	{
		this.webSite = webSite;
	}
	public IWebSiteService getWebSiteService()
	{
		return webSiteService;
	}
	public void setWebSiteService(IWebSiteService webSiteService)
	{
		this.webSiteService = webSiteService;
	}
	public List<Team> getGny_tejia()
	{
		return gny_tejia;
	}
	public void setGny_tejia(List<Team> gny_tejia)
	{
		this.gny_tejia = gny_tejia;
	}
	public ITeamService getTeamService()
	{
		return teamService;
	}
	public void setTeamService(ITeamService teamService)
	{
		this.teamService = teamService;
	}
	public List<ThemeFlash> getCsjd() {
		return csjd;
	}
	public void setCsjd(List<ThemeFlash> csjd) {
		this.csjd = csjd;
	}
	public List<ScenerysOfType> getCsjd_jxtj()
	{
		return csjd_jxtj;
	}
	public void setCsjd_jxtj(List<ScenerysOfType> csjd_jxtj)
	{
		this.csjd_jxtj = csjd_jxtj;
	}
	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}
	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
	}
	public ISceneryTypeService getSceneryTypeService()
	{
		return sceneryTypeService;
	}
	public void setSceneryTypeService(ISceneryTypeService sceneryTypeService)
	{
		this.sceneryTypeService = sceneryTypeService;
	}
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
	}
	public List<News> getZyx_hkjd()
	{
		return zyx_hkjd;
	}
	public void setZyx_hkjd(List<News> zyx_hkjd)
	{
		this.zyx_hkjd = zyx_hkjd;
	}
	public TripModel getCsjd_class_gj1()
	{
		return csjd_class_gj1;
	}
	public void setCsjd_class_gj1(TripModel csjd_class_gj1)
	{
		this.csjd_class_gj1 = csjd_class_gj1;
	}
	public TripModel getCsjd_class_gj2()
	{
		return csjd_class_gj2;
	}
	public void setCsjd_class_gj2(TripModel csjd_class_gj2)
	{
		this.csjd_class_gj2 = csjd_class_gj2;
	}
	public TripModel getCsjd_class_gj3()
	{
		return csjd_class_gj3;
	}
	public void setCsjd_class_gj3(TripModel csjd_class_gj3)
	{
		this.csjd_class_gj3 = csjd_class_gj3;
	}
	public TripModel getCsjd_class_gj4()
	{
		return csjd_class_gj4;
	}
	public void setCsjd_class_gj4(TripModel csjd_class_gj4)
	{
		this.csjd_class_gj4 = csjd_class_gj4;
	}
	public TripModel getCsjd_class_gj5()
	{
		return csjd_class_gj5;
	}
	public void setCsjd_class_gj5(TripModel csjd_class_gj5)
	{
		this.csjd_class_gj5 = csjd_class_gj5;
	}
	public TripModel getCsjd_class_gj6()
	{
		return csjd_class_gj6;
	}
	public void setCsjd_class_gj6(TripModel csjd_class_gj6)
	{
		this.csjd_class_gj6 = csjd_class_gj6;
	}
	public List<TripContent> getCsjd_gj1()
	{
		return csjd_gj1;
	}
	public void setCsjd_gj1(List<TripContent> csjd_gj1)
	{
		this.csjd_gj1 = csjd_gj1;
	}
	public List<TripContent> getCsjd_gj2()
	{
		return csjd_gj2;
	}
	public void setCsjd_gj2(List<TripContent> csjd_gj2)
	{
		this.csjd_gj2 = csjd_gj2;
	}
	public List<TripContent> getCsjd_gj3()
	{
		return csjd_gj3;
	}
	public void setCsjd_gj3(List<TripContent> csjd_gj3)
	{
		this.csjd_gj3 = csjd_gj3;
	}
	public List<TripContent> getCsjd_gj4()
	{
		return csjd_gj4;
	}
	public void setCsjd_gj4(List<TripContent> csjd_gj4)
	{
		this.csjd_gj4 = csjd_gj4;
	}
	public List<TripContent> getCsjd_gj5()
	{
		return csjd_gj5;
	}
	public void setCsjd_gj5(List<TripContent> csjd_gj5)
	{
		this.csjd_gj5 = csjd_gj5;
	}
	public List<TripContent> getCsjd_gj6()
	{
		return csjd_gj6;
	}
	public void setCsjd_gj6(List<TripContent> csjd_gj6)
	{
		this.csjd_gj6 = csjd_gj6;
	}
	public ITripModelService getTripModelService()
	{
		return tripModelService;
	}
	public void setTripModelService(ITripModelService tripModelService)
	{
		this.tripModelService = tripModelService;
	}
	public List<TripContent> getQytd_yx()
	{
		return qytd_yx;
	}
	public void setQytd_yx(List<TripContent> qytd_yx)
	{
		this.qytd_yx = qytd_yx;
	}
	public List<TripContent> getSy_gnxws()
	{
		return sy_gnxws;
	}
	public void setSy_gnxws(List<TripContent> sy_gnxws)
	{
		this.sy_gnxws = sy_gnxws;
	}
	public List<TripContent> getSy_gwxws()
	{
		return sy_gwxws;
	}
	public void setSy_gwxws(List<TripContent> sy_gwxws)
	{
		this.sy_gwxws = sy_gwxws;
	}
	public List<TripContent> getSzsy_yx()
	{
		return szsy_yx;
	}
	public void setSzsy_yx(List<TripContent> szsy_yx)
	{
		this.szsy_yx = szsy_yx;
	}
	public List<TripContent> getGny_tht()
	{
		return gny_tht;
	}
	public void setGny_tht(List<TripContent> gny_tht)
	{
		this.gny_tht = gny_tht;
	}
	public List<Region> getChujingList()
	{
		return chujingList;
	}
	public void setChujingList(List<Region> chujingList)
	{
		this.chujingList = chujingList;
	}
	public List<Region> getGuoneiList()
	{
		return guoneiList;
	}
	public void setGuoneiList(List<Region> guoneiList)
	{
		this.guoneiList = guoneiList;
	}
	public List<Region> getZhoubianList()
	{
		return zhoubianList;
	}
	public void setZhoubianList(List<Region> zhoubianList)
	{
		this.zhoubianList = zhoubianList;
	}
	public List<Region> getZiyouxingList()
	{
		return ziyouxingList;
	}
	public void setZiyouxingList(List<Region> ziyouxingList)
	{
		this.ziyouxingList = ziyouxingList;
	}
	public List<News> getRightHelps()
	{
		return rightHelps;
	}
	public void setRightHelps(List<News> rightHelps)
	{
		this.rightHelps = rightHelps;
	}

}
