/*
 * Copyright (c) 2006 SK C&C. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance wih the terms of the license agreement you entered into with SK
 * C&C.
 */

package fwk.code.internal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import nexcore.framework.core.cache.ICache;
import nexcore.framework.core.cache.ICacheManager;
import nexcore.framework.core.prototype.AbsFwkService;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.integration.db.ISqlManager;
import nexcore.framework.core.util.StringUtils;
import fwk.code.HpcCodeManager;
import fwk.constants.DmsConstants;

/**
 * <ul>
 * <li>업무 그룹명 : 금융 프레임워크 </li>
 * <li>서브 업무명 : 공통모듈</li>
 * <li>설 명 : 공통코드 관리</li>
 * <li>작성일 : 2007. 4. 12.</li>
 * <li>작성자 : 이상은</li>
 * </ul>
 */
public class CachedHpcCodeManager extends AbsFwkService implements HpcCodeManager, java.util.Observer {

    protected ISqlManager         sm;

    protected ICacheManager       icm;

    protected static final String cacheName        = DmsConstants.CODE_CACHE_NAME;

    protected String[]            supportedLocales = null;

    protected boolean controlByDate = true;
    
    protected boolean controlByActiveness = false;
    
    public void setCacheManager(ICacheManager cm) {
        this.icm = cm;
    }
    
    public void setControlByDate(boolean controlByDate){
        this.controlByDate = controlByDate;
    }
    
    public void setControlByActiveness(boolean controlByActiveness){
        this.controlByActiveness = controlByActiveness;
    }

    public void init() {
    	refresh();
    	icm.addObserver(cacheName, this);

        super.init();
    }

    protected ICache getCache() {
        ICache cache = icm.getCache(cacheName);
        if (cache == null) {
            icm.addCache(cacheName);
            cache = icm.getCache(cacheName);
        }
        return cache;
    }

    public String[] getSupportedLocales() {
        String supportedLocales = BaseUtils
                .getConfiguration("locale.supported");
        if (supportedLocales != null) {
            this.supportedLocales = supportedLocales.split(",");
        }
        return this.supportedLocales;
    }

    public List<String> getCodeGroupIds() {
        // TODO find a good way to relate with cache
        List<String> codes = sm.queryForList("hpc.code.getCodeGroups");
        return codes;
    }


    /**
     * 
     * @param groupId
     * @param dump
     * @return
     */
    public List<HpcCode> getCodes(String groupId) {
        // try to take from cache
        List<HpcCode> cg = (List<HpcCode>) getCache().get(groupId);
        if (cg == null) {
            // take from database and put to cache
            cg = getCodeGroupBySql(groupId);
            getCache().put(groupId, cg);
        }
        return cg;
    }


    /**
     * 
     * @param groupId
     * @param codeId
     * @return
     */
    public HpcCode getCode(String groupId, String codeId) {
        //2015.10.13 jihooyim code inspector 점검 수정 (하드코딩 된 값과 문자열 equal 비교)
        if (StringUtils.isEmpty(codeId)){
            return null;
        }
        List<HpcCode> cg = getCodes(groupId);
        if(cg == null){
        	return null;
        }
        Iterator<HpcCode> all = cg.iterator();
        while (all.hasNext()) {
        	HpcCode c = (HpcCode) all.next();
            if (codeId.equals(c.getCodeId())) {
                return c;
            }
        }
        // 없다.
        return null;
    }

    public void setSqlManager(ISqlManager sm) {
        this.sm = sm;
    }

    /**
     * @param groupId
     * @return List<ICode>
     */
    protected List<HpcCode> getCodeGroupBySql(String groupId) {
    	List<HpcCode>hpcCodeList = new ArrayList<HpcCode>();
    	Map<String, HpcCode> codes = new LinkedHashMap<String, HpcCode>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("CM_GRP_CD_ID", groupId);
        List<Map<String, Object>> codesBundle = sm.queryForList("hpc.code.getCodes", param);
        for (Map<String, Object> each : codesBundle) {
            String codeId = (String) each.get("CM_CD_ID");
            
            HpcCode code = codes.get(codeId);
            if(code == null){
            	code = new HpcCode();
            	code.setCodeId(codeId);
                code.setGroupCdId(groupId);
            	codes.put(codeId, code);
            }
            
            code.setGroupCdNm((String) each.get("CM_GRP_CD_NM"));
            code.setGroupCdDesc((String) each.get("CM_GRP_CD_DESC"));
            code.setMgmtCdYn((String) each.get("MGMT_CD_YN"));
            code.setSupCmGrpCdId((String) each.get("SUP_CM_GRP_CD_ID"));
            code.setSupCmCdId((String) each.get("SUP_CM_CD_ID"));
            code.setCodeNm((String) each.get("CM_CD_NM"));
            code.setCodeDesc((String) each.get("CM_CD_DESC"));
            code.setSortOrd(((BigDecimal) each.get("CM_CD_SORT_ORD")).intValue());
            code.setMgmtItemCd1((String) each.get("MGMT_ITEM_CD1"));
            code.setMgmtItemCd2((String) each.get("MGMT_ITEM_CD2"));
            code.setMgmtItemCd3((String) each.get("MGMT_ITEM_CD3"));
            code.setMgmtItemCd4((String) each.get("MGMT_ITEM_CD4"));
            code.setMgmtItemCd5((String) each.get("MGMT_ITEM_CD5"));
            code.setMgmtItemCd6((String) each.get("MGMT_ITEM_CD6"));
            code.setMgmtItemCd7((String) each.get("MGMT_ITEM_CD7"));
            code.setMgmtItemCd8((String) each.get("MGMT_ITEM_CD8"));
            code.setMgmtItemCd9((String) each.get("MGMT_ITEM_CD9"));
            code.setMgmtItemCd10((String) each.get("MGMT_ITEM_CD10"));
            code.setMgmtItemNm1((String) each.get("MGMT_ITEM_NM1"));
            code.setMgmtItemNm2((String) each.get("MGMT_ITEM_NM2"));
            code.setMgmtItemNm3((String) each.get("MGMT_ITEM_NM3"));
            code.setMgmtItemNm4((String) each.get("MGMT_ITEM_NM4"));
            code.setMgmtItemNm5((String) each.get("MGMT_ITEM_NM5"));
            code.setMgmtItemNm6((String) each.get("MGMT_ITEM_NM6"));
            code.setMgmtItemNm7((String) each.get("MGMT_ITEM_NM7"));
            code.setMgmtItemNm8((String) each.get("MGMT_ITEM_NM8"));
            code.setMgmtItemNm9((String) each.get("MGMT_ITEM_NM9"));
            code.setMgmtItemNm10((String) each.get("MGMT_ITEM_NM10"));
            hpcCodeList.add(code);
        }
        
        return hpcCodeList;
    }

    /**
     * @see nexcore.framework.core.prototype.IRefresh#refresh()
     */
    public void refresh() {
    	ICache cache = getCache();
        if (cache != null) {
            cache.removeAll();
        }
    }

    /**
     * 주어진 group id에 해당하는 캐시된 코드들을 삭제한다.
     * 
     * @see nexcore.framework.core.prototype.IRefresh#refresh(java.lang.String)
     */
    public void refresh(String groupId) {
    	ICache cache = getCache();
        if (cache != null && cache.containsKey(groupId)) {
            cache.remove(groupId);
        }
    }

	public void update(Observable o, Object value) {
		String removedCacheName = (String) value;
		//전체 or 지금 사용하는 캐시 remove
		if (removedCacheName == null || removedCacheName.equals(cacheName)) {
			refresh();
		}
	}
}
