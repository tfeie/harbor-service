package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyUserFansCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyUserFansCriteria() {
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

        public Criteria andFansIdIsNull() {
            addCriterion("FANS_ID is null");
            return (Criteria) this;
        }

        public Criteria andFansIdIsNotNull() {
            addCriterion("FANS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFansIdEqualTo(String value) {
            addCriterion("FANS_ID =", value, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdNotEqualTo(String value) {
            addCriterion("FANS_ID <>", value, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdGreaterThan(String value) {
            addCriterion("FANS_ID >", value, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdGreaterThanOrEqualTo(String value) {
            addCriterion("FANS_ID >=", value, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdLessThan(String value) {
            addCriterion("FANS_ID <", value, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdLessThanOrEqualTo(String value) {
            addCriterion("FANS_ID <=", value, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdLike(String value) {
            addCriterion("FANS_ID like", value, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdNotLike(String value) {
            addCriterion("FANS_ID not like", value, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdIn(List<String> values) {
            addCriterion("FANS_ID in", values, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdNotIn(List<String> values) {
            addCriterion("FANS_ID not in", values, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdBetween(String value1, String value2) {
            addCriterion("FANS_ID between", value1, value2, "fansId");
            return (Criteria) this;
        }

        public Criteria andFansIdNotBetween(String value1, String value2) {
            addCriterion("FANS_ID not between", value1, value2, "fansId");
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

        public Criteria andFansUserIdIsNull() {
            addCriterion("FANS_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andFansUserIdIsNotNull() {
            addCriterion("FANS_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFansUserIdEqualTo(String value) {
            addCriterion("FANS_USER_ID =", value, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdNotEqualTo(String value) {
            addCriterion("FANS_USER_ID <>", value, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdGreaterThan(String value) {
            addCriterion("FANS_USER_ID >", value, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("FANS_USER_ID >=", value, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdLessThan(String value) {
            addCriterion("FANS_USER_ID <", value, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdLessThanOrEqualTo(String value) {
            addCriterion("FANS_USER_ID <=", value, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdLike(String value) {
            addCriterion("FANS_USER_ID like", value, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdNotLike(String value) {
            addCriterion("FANS_USER_ID not like", value, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdIn(List<String> values) {
            addCriterion("FANS_USER_ID in", values, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdNotIn(List<String> values) {
            addCriterion("FANS_USER_ID not in", values, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdBetween(String value1, String value2) {
            addCriterion("FANS_USER_ID between", value1, value2, "fansUserId");
            return (Criteria) this;
        }

        public Criteria andFansUserIdNotBetween(String value1, String value2) {
            addCriterion("FANS_USER_ID not between", value1, value2, "fansUserId");
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

        public Criteria andStsChgDateIsNull() {
            addCriterion("STS_CHG_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStsChgDateIsNotNull() {
            addCriterion("STS_CHG_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStsChgDateEqualTo(Timestamp value) {
            addCriterion("STS_CHG_DATE =", value, "stsChgDate");
            return (Criteria) this;
        }

        public Criteria andStsChgDateNotEqualTo(Timestamp value) {
            addCriterion("STS_CHG_DATE <>", value, "stsChgDate");
            return (Criteria) this;
        }

        public Criteria andStsChgDateGreaterThan(Timestamp value) {
            addCriterion("STS_CHG_DATE >", value, "stsChgDate");
            return (Criteria) this;
        }

        public Criteria andStsChgDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("STS_CHG_DATE >=", value, "stsChgDate");
            return (Criteria) this;
        }

        public Criteria andStsChgDateLessThan(Timestamp value) {
            addCriterion("STS_CHG_DATE <", value, "stsChgDate");
            return (Criteria) this;
        }

        public Criteria andStsChgDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("STS_CHG_DATE <=", value, "stsChgDate");
            return (Criteria) this;
        }

        public Criteria andStsChgDateIn(List<Timestamp> values) {
            addCriterion("STS_CHG_DATE in", values, "stsChgDate");
            return (Criteria) this;
        }

        public Criteria andStsChgDateNotIn(List<Timestamp> values) {
            addCriterion("STS_CHG_DATE not in", values, "stsChgDate");
            return (Criteria) this;
        }

        public Criteria andStsChgDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("STS_CHG_DATE between", value1, value2, "stsChgDate");
            return (Criteria) this;
        }

        public Criteria andStsChgDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("STS_CHG_DATE not between", value1, value2, "stsChgDate");
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