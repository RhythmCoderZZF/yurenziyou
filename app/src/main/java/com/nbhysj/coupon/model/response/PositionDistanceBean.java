package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/5/20
 * description：位置距离
 */
public class PositionDistanceBean {

    private int id;

    private boolean isSelect;

    private String positionDistanceType;

    private List<PositionDistanceTypeClassifyTwoLevel> classifyTwoLevelList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getPositionDistanceType() {
        return positionDistanceType;
    }

    public List<PositionDistanceTypeClassifyTwoLevel> getClassifyTwoLevelList() {
        return classifyTwoLevelList;
    }

    public void setClassifyTwoLevelList(List<PositionDistanceTypeClassifyTwoLevel> classifyTwoLevelList) {
        this.classifyTwoLevelList = classifyTwoLevelList;
    }

    public void setPositionDistanceType(String positionDistanceType) {
        this.positionDistanceType = positionDistanceType;
    }

    public class PositionDistanceTypeClassifyTwoLevel {

        private String twoLevelName;

        private boolean isSelect;

        private List<PositionDistanceTypeClassifyThreeLevel> classifyThreeLevelList;

        public List<PositionDistanceTypeClassifyThreeLevel> getClassifyThreeLevelList() {
            return classifyThreeLevelList;
        }

        public void setClassifyThreeLevelList(List<PositionDistanceTypeClassifyThreeLevel> classifyThreeLevelList) {
            this.classifyThreeLevelList = classifyThreeLevelList;
        }

        public String getTwoLevelName() {
            return twoLevelName;
        }

        public void setTwoLevelName(String twoLevelName) {
            this.twoLevelName = twoLevelName;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public class PositionDistanceTypeClassifyThreeLevel {

        private String threeLevelName;

        private boolean isSelect;

        public String getThreeLevelName() {
            return threeLevelName;
        }

        public void setThreeLevelName(String threeLevelName) {
            this.threeLevelName = threeLevelName;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

    }
}
