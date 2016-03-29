package dms.fw.fwsbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/FWK</li>
 * <li>설 명 : </li>
 * <li>작성일 : 2015-07-13 12:41:32</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class FWSBaseBean extends nexcore.framework.coreext.pojo.biz.SharedBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.SharedBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "fw.FWSBase";
    }

}
