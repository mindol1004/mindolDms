package fwk.code;

import java.util.List;

import nexcore.framework.core.prototype.IFwkService;
import nexcore.framework.core.prototype.IRefresh;
import fwk.code.internal.HpcCode;

public interface HpcCodeManager extends IFwkService, IRefresh {
	  /**
     * 코드 그룹 아이디들을 (모두) 받아온다.
     * 
     * @return List<String>
     */
    List<String> getCodeGroupIds();

    /**
     * 코드 그룹 아이디를 가지고 코드(의 <code>List</code>)를 받아온다.
     * 
     * @param groupId
     *            코드 그룹 아이디
     * @return List<HpcCode> 코드 리스트
     */
    List<HpcCode> getCodes(String groupId);
    
  
    /**
     * 선택된 코드그룹안에 있는 코드정보를 가지고 온다.
     * @param groupId
     * @param codeId
     * @return
     */
    HpcCode getCode(String groupId, String codeId);
    
    /**
     * 캐싱된 데이터 모두를 리셋한다.
     */
    void refresh();

    /**
     * 지정한 group에 대한 캐시 데이터를 리셋한다.
     * 
     * @param groupId
     *            리셋할 group의 id
     */
    void refresh(String groupId);
}
