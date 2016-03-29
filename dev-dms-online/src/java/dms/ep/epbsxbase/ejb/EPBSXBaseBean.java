package dms.ep.epbsxbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>설 명 : </li>
 * <li>작성일 : 2015-10-05 10:32:44</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 *
 * @author 김윤환 (kyh0904)
 */
public class EPBSXBaseBean extends nexcore.framework.coreext.pojo.biz.OnlineBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.OnlineBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "ep.EPBSXBase";
    }

}
