package dms.ep.epsfsbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>설 명 : </li>
 * <li>작성일 : 2016-02-16 09:50:45</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class EPSFSBaseBean extends nexcore.framework.coreext.pojo.biz.SharedBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.SharedBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "ep.EPSFSBase";
    }

}
