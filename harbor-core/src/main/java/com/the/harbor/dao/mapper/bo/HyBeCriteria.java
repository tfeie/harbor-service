package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyBeCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyBeCriteria() {
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

        public Criteria andBeIdIsNull() {
            addCriterion("BE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBeIdIsNotNull() {
            addCriterion("BE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBeIdEqualTo(String value) {
            addCriterion("BE_ID =", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdNotEqualTo(String value) {
            addCriterion("BE_ID <>", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdGreaterThan(String value) {
            addCriterion("BE_ID >", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdGreaterThanOrEqualTo(String value) {
            addCriterion("BE_ID >=", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdLessThan(String value) {
            addCriterion("BE_ID <", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdLessThanOrEqualTo(String value) {
            addCriterion("BE_ID <=", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdLike(String value) {
            addCriterion("BE_ID like", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdNotLike(String value) {
            addCriterion("BE_ID not like", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdIn(List<String> values) {
            addCriterion("BE_ID in", values, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdNotIn(List<String> values) {
            addCriterion("BE_ID not in", values, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdBetween(String value1, String value2) {
            addCriterion("BE_ID between", value1, value2, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdNotBetween(String value1, String value2) {
            addCriterion("BE_ID not between", value1, value2, "beId");
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

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Timestamp value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Timestamp value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Timestamp> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Timestamp> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNull() {
            addCriterion("INVALID_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNotNull() {
            addCriterion("INVALID_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE =", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE <>", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThan(Timestamp value) {
            addCriterion("INVALID_DATE >", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE >=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThan(Timestamp value) {
            addCriterion("INVALID_DATE <", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE <=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIn(List<Timestamp> values) {
            addCriterion("INVALID_DATE in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotIn(List<Timestamp> values) {
            addCriterion("INVALID_DATE not in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_DATE between", value1, value2, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_DATE not between", value1, value2, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andTopFlagIsNull() {
            addCriterion("TOP_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andTopFlagIsNotNull() {
            addCriterion("TOP_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTopFlagEqualTo(String value) {
            addCriterion("TOP_FLAG =", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagNotEqualTo(String value) {
            addCriterion("TOP_FLAG <>", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagGreaterThan(String value) {
            addCriterion("TOP_FLAG >", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagGreaterThanOrEqualTo(String value) {
            addCriterion("TOP_FLAG >=", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagLessThan(String value) {
            addCriterion("TOP_FLAG <", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagLessThanOrEqualTo(String value) {
            addCriterion("TOP_FLAG <=", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagLike(String value) {
            addCriterion("TOP_FLAG like", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagNotLike(String value) {
            addCriterion("TOP_FLAG not like", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagIn(List<String> values) {
            addCriterion("TOP_FLAG in", values, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagNotIn(List<String> values) {
            addCriterion("TOP_FLAG not in", values, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagBetween(String value1, String value2) {
            addCriterion("TOP_FLAG between", value1, value2, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagNotBetween(String value1, String value2) {
            addCriterion("TOP_FLAG not between", value1, value2, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopDateIsNull() {
            addCriterion("TOP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTopDateIsNotNull() {
            addCriterion("TOP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTopDateEqualTo(Timestamp value) {
            addCriterion("TOP_DATE =", value, "topDate");
            return (Criteria) this;
        }

        public Criteria andTopDateNotEqualTo(Timestamp value) {
            addCriterion("TOP_DATE <>", value, "topDate");
            return (Criteria) this;
        }

        public Criteria andTopDateGreaterThan(Timestamp value) {
            addCriterion("TOP_DATE >", value, "topDate");
            return (Criteria) this;
        }

        public Criteria andTopDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("TOP_DATE >=", value, "topDate");
            return (Criteria) this;
        }

        public Criteria andTopDateLessThan(Timestamp value) {
            addCriterion("TOP_DATE <", value, "topDate");
            return (Criteria) this;
        }

        public Criteria andTopDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("TOP_DATE <=", value, "topDate");
            return (Criteria) this;
        }

        public Criteria andTopDateIn(List<Timestamp> values) {
            addCriterion("TOP_DATE in", values, "topDate");
            return (Criteria) this;
        }

        public Criteria andTopDateNotIn(List<Timestamp> values) {
            addCriterion("TOP_DATE not in", values, "topDate");
            return (Criteria) this;
        }

        public Criteria andTopDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TOP_DATE between", value1, value2, "topDate");
            return (Criteria) this;
        }

        public Criteria andTopDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TOP_DATE not between", value1, value2, "topDate");
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