package com.the.harbor.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class HyUserHbAssetsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyUserHbAssetsCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAssetsIdIsNull() {
            addCriterion("ASSETS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAssetsIdIsNotNull() {
            addCriterion("ASSETS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAssetsIdEqualTo(String value) {
            addCriterion("ASSETS_ID =", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdNotEqualTo(String value) {
            addCriterion("ASSETS_ID <>", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdGreaterThan(String value) {
            addCriterion("ASSETS_ID >", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETS_ID >=", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdLessThan(String value) {
            addCriterion("ASSETS_ID <", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdLessThanOrEqualTo(String value) {
            addCriterion("ASSETS_ID <=", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdLike(String value) {
            addCriterion("ASSETS_ID like", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdNotLike(String value) {
            addCriterion("ASSETS_ID not like", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdIn(List<String> values) {
            addCriterion("ASSETS_ID in", values, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdNotIn(List<String> values) {
            addCriterion("ASSETS_ID not in", values, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdBetween(String value1, String value2) {
            addCriterion("ASSETS_ID between", value1, value2, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdNotBetween(String value1, String value2) {
            addCriterion("ASSETS_ID not between", value1, value2, "assetsId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangIsNull() {
            addCriterion("TOTAL_BEISHANG is null");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangIsNotNull() {
            addCriterion("TOTAL_BEISHANG is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangEqualTo(long value) {
            addCriterion("TOTAL_BEISHANG =", value, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangNotEqualTo(long value) {
            addCriterion("TOTAL_BEISHANG <>", value, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangGreaterThan(long value) {
            addCriterion("TOTAL_BEISHANG >", value, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangGreaterThanOrEqualTo(long value) {
            addCriterion("TOTAL_BEISHANG >=", value, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangLessThan(long value) {
            addCriterion("TOTAL_BEISHANG <", value, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangLessThanOrEqualTo(long value) {
            addCriterion("TOTAL_BEISHANG <=", value, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangIn(List<Long> values) {
            addCriterion("TOTAL_BEISHANG in", values, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangNotIn(List<Long> values) {
            addCriterion("TOTAL_BEISHANG not in", values, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangBetween(long value1, long value2) {
            addCriterion("TOTAL_BEISHANG between", value1, value2, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalBeishangNotBetween(long value1, long value2) {
            addCriterion("TOTAL_BEISHANG not between", value1, value2, "totalBeishang");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliIsNull() {
            addCriterion("TOTAL_JIANGLI is null");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliIsNotNull() {
            addCriterion("TOTAL_JIANGLI is not null");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliEqualTo(long value) {
            addCriterion("TOTAL_JIANGLI =", value, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliNotEqualTo(long value) {
            addCriterion("TOTAL_JIANGLI <>", value, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliGreaterThan(long value) {
            addCriterion("TOTAL_JIANGLI >", value, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliGreaterThanOrEqualTo(long value) {
            addCriterion("TOTAL_JIANGLI >=", value, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliLessThan(long value) {
            addCriterion("TOTAL_JIANGLI <", value, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliLessThanOrEqualTo(long value) {
            addCriterion("TOTAL_JIANGLI <=", value, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliIn(List<Long> values) {
            addCriterion("TOTAL_JIANGLI in", values, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliNotIn(List<Long> values) {
            addCriterion("TOTAL_JIANGLI not in", values, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliBetween(long value1, long value2) {
            addCriterion("TOTAL_JIANGLI between", value1, value2, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalJiangliNotBetween(long value1, long value2) {
            addCriterion("TOTAL_JIANGLI not between", value1, value2, "totalJiangli");
            return (Criteria) this;
        }

        public Criteria andTotalDashangIsNull() {
            addCriterion("TOTAL_DASHANG is null");
            return (Criteria) this;
        }

        public Criteria andTotalDashangIsNotNull() {
            addCriterion("TOTAL_DASHANG is not null");
            return (Criteria) this;
        }

        public Criteria andTotalDashangEqualTo(long value) {
            addCriterion("TOTAL_DASHANG =", value, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalDashangNotEqualTo(long value) {
            addCriterion("TOTAL_DASHANG <>", value, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalDashangGreaterThan(long value) {
            addCriterion("TOTAL_DASHANG >", value, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalDashangGreaterThanOrEqualTo(long value) {
            addCriterion("TOTAL_DASHANG >=", value, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalDashangLessThan(long value) {
            addCriterion("TOTAL_DASHANG <", value, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalDashangLessThanOrEqualTo(long value) {
            addCriterion("TOTAL_DASHANG <=", value, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalDashangIn(List<Long> values) {
            addCriterion("TOTAL_DASHANG in", values, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalDashangNotIn(List<Long> values) {
            addCriterion("TOTAL_DASHANG not in", values, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalDashangBetween(long value1, long value2) {
            addCriterion("TOTAL_DASHANG between", value1, value2, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalDashangNotBetween(long value1, long value2) {
            addCriterion("TOTAL_DASHANG not between", value1, value2, "totalDashang");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiIsNull() {
            addCriterion("TOTAL_GONGYI is null");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiIsNotNull() {
            addCriterion("TOTAL_GONGYI is not null");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiEqualTo(long value) {
            addCriterion("TOTAL_GONGYI =", value, "totalGongyi");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiNotEqualTo(long value) {
            addCriterion("TOTAL_GONGYI <>", value, "totalGongyi");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiGreaterThan(long value) {
            addCriterion("TOTAL_GONGYI >", value, "totalGongyi");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiGreaterThanOrEqualTo(long value) {
            addCriterion("TOTAL_GONGYI >=", value, "totalGongyi");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiLessThan(long value) {
            addCriterion("TOTAL_GONGYI <", value, "totalGongyi");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiLessThanOrEqualTo(long value) {
            addCriterion("TOTAL_GONGYI <=", value, "totalGongyi");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiIn(List<Long> values) {
            addCriterion("TOTAL_GONGYI in", values, "totalGongyi");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiNotIn(List<Long> values) {
            addCriterion("TOTAL_GONGYI not in", values, "totalGongyi");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiBetween(long value1, long value2) {
            addCriterion("TOTAL_GONGYI between", value1, value2, "totalGongyi");
            return (Criteria) this;
        }

        public Criteria andTotalGongyiNotBetween(long value1, long value2) {
            addCriterion("TOTAL_GONGYI not between", value1, value2, "totalGongyi");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}