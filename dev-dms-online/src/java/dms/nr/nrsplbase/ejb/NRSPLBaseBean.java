package dms.nr.nrsplbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>설 명 : </li>
 * <li>작성일 : 2015-07-16 14:15:56</li>
 * <li>작성자 : 박홍서 (uni9401)</li>
 * </ul>
 *
 * @author 박홍서 (uni9401)
 */
public class NRSPLBaseBean extends nexcore.framework.coreext.pojo.biz.SharedBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.SharedBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "nr.NRSPLBase";
    }

}
