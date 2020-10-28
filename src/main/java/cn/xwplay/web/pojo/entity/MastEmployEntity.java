package cn.xwplay.web.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MastEmployEntity {

    /**
     * 直間区分（直接税と間接税との比率）
     */

    /**
     * 給与区分
     */
    private String salaryType;
    /**
     * 給与区分コード
     */
    private String salaryTypeCode;
    /**
     * 国籍
     */
    private String nationality;
    /**
     * 国籍コード
     */
    private String nationalityCode;
    /**
     * 本籍地
     */
    private String originNationality;
    /**
     * 本籍地コード
     */
    private String originNationalityCode;
    /**
     * バージョンNo
     */
    private Long version;

}
