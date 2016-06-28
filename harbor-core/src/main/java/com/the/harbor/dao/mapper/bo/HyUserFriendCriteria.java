package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyUserFriendCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyUserFriendCriteria() {
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

        public Criteria andRecordIdIsNull() {
            addCriterion("RECORD_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("RECORD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(String value) {
            addCriterion("RECORD_ID =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(String value) {
            addCriterion("RECORD_ID <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(String value) {
            addCriterion("RECORD_ID >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_ID >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(String value) {
            addCriterion("RECORD_ID <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(String value) {
            addCriterion("RECORD_ID <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLike(String value) {
            addCriterion("RECORD_ID like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotLike(String value) {
            addCriterion("RECORD_ID not like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<String> values) {
            addCriterion("RECORD_ID in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<String> values) {
            addCriterion("RECORD_ID not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(String value1, String value2) {
            addCriterion("RECORD_ID between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(String value1, String value2) {
            addCriterion("RECORD_ID not between", value1, value2, "recordId");
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

        public Criteria andFriendIdIsNull() {
            addCriterion("FRIEND_ID is null");
            return (Criteria) this;
        }

        public Criteria andFriendIdIsNotNull() {
            addCriterion("FRIEND_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFriendIdEqualTo(String value) {
            addCriterion("FRIEND_ID =", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotEqualTo(String value) {
            addCriterion("FRIEND_ID <>", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdGreaterThan(String value) {
            addCriterion("FRIEND_ID >", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdGreaterThanOrEqualTo(String value) {
            addCriterion("FRIEND_ID >=", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdLessThan(String value) {
            addCriterion("FRIEND_ID <", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdLessThanOrEqualTo(String value) {
            addCriterion("FRIEND_ID <=", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdLike(String value) {
            addCriterion("FRIEND_ID like", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotLike(String value) {
            addCriterion("FRIEND_ID not like", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdIn(List<String> values) {
            addCriterion("FRIEND_ID in", values, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotIn(List<String> values) {
            addCriterion("FRIEND_ID not in", values, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdBetween(String value1, String value2) {
            addCriterion("FRIEND_ID between", value1, value2, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotBetween(String value1, String value2) {
            addCriterion("FRIEND_ID not between", value1, value2, "friendId");
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

        public Criteria andApplyMqIsNull() {
            addCriterion("APPLY_MQ is null");
            return (Criteria) this;
        }

        public Criteria andApplyMqIsNotNull() {
            addCriterion("APPLY_MQ is not null");
            return (Criteria) this;
        }

        public Criteria andApplyMqEqualTo(String value) {
            addCriterion("APPLY_MQ =", value, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqNotEqualTo(String value) {
            addCriterion("APPLY_MQ <>", value, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqGreaterThan(String value) {
            addCriterion("APPLY_MQ >", value, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_MQ >=", value, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqLessThan(String value) {
            addCriterion("APPLY_MQ <", value, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqLessThanOrEqualTo(String value) {
            addCriterion("APPLY_MQ <=", value, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqLike(String value) {
            addCriterion("APPLY_MQ like", value, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqNotLike(String value) {
            addCriterion("APPLY_MQ not like", value, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqIn(List<String> values) {
            addCriterion("APPLY_MQ in", values, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqNotIn(List<String> values) {
            addCriterion("APPLY_MQ not in", values, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqBetween(String value1, String value2) {
            addCriterion("APPLY_MQ between", value1, value2, "applyMq");
            return (Criteria) this;
        }

        public Criteria andApplyMqNotBetween(String value1, String value2) {
            addCriterion("APPLY_MQ not between", value1, value2, "applyMq");
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

        public Criteria andStsDateIsNull() {
            addCriterion("STS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStsDateIsNotNull() {
            addCriterion("STS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStsDateEqualTo(Timestamp value) {
            addCriterion("STS_DATE =", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateNotEqualTo(Timestamp value) {
            addCriterion("STS_DATE <>", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateGreaterThan(Timestamp value) {
            addCriterion("STS_DATE >", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("STS_DATE >=", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateLessThan(Timestamp value) {
            addCriterion("STS_DATE <", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("STS_DATE <=", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateIn(List<Timestamp> values) {
            addCriterion("STS_DATE in", values, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateNotIn(List<Timestamp> values) {
            addCriterion("STS_DATE not in", values, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("STS_DATE between", value1, value2, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("STS_DATE not between", value1, value2, "stsDate");
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