package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyGoCommentsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyGoCommentsCriteria() {
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

        public Criteria andCommentIdIsNull() {
            addCriterion("COMMENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNotNull() {
            addCriterion("COMMENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCommentIdEqualTo(String value) {
            addCriterion("COMMENT_ID =", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotEqualTo(String value) {
            addCriterion("COMMENT_ID <>", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThan(String value) {
            addCriterion("COMMENT_ID >", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMMENT_ID >=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThan(String value) {
            addCriterion("COMMENT_ID <", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThanOrEqualTo(String value) {
            addCriterion("COMMENT_ID <=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLike(String value) {
            addCriterion("COMMENT_ID like", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotLike(String value) {
            addCriterion("COMMENT_ID not like", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIn(List<String> values) {
            addCriterion("COMMENT_ID in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotIn(List<String> values) {
            addCriterion("COMMENT_ID not in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdBetween(String value1, String value2) {
            addCriterion("COMMENT_ID between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotBetween(String value1, String value2) {
            addCriterion("COMMENT_ID not between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andGoIdIsNull() {
            addCriterion("GO_ID is null");
            return (Criteria) this;
        }

        public Criteria andGoIdIsNotNull() {
            addCriterion("GO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGoIdEqualTo(String value) {
            addCriterion("GO_ID =", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdNotEqualTo(String value) {
            addCriterion("GO_ID <>", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdGreaterThan(String value) {
            addCriterion("GO_ID >", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdGreaterThanOrEqualTo(String value) {
            addCriterion("GO_ID >=", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdLessThan(String value) {
            addCriterion("GO_ID <", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdLessThanOrEqualTo(String value) {
            addCriterion("GO_ID <=", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdLike(String value) {
            addCriterion("GO_ID like", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdNotLike(String value) {
            addCriterion("GO_ID not like", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdIn(List<String> values) {
            addCriterion("GO_ID in", values, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdNotIn(List<String> values) {
            addCriterion("GO_ID not in", values, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdBetween(String value1, String value2) {
            addCriterion("GO_ID between", value1, value2, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdNotBetween(String value1, String value2) {
            addCriterion("GO_ID not between", value1, value2, "goId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdIsNull() {
            addCriterion("PUBLISH_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdIsNotNull() {
            addCriterion("PUBLISH_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdEqualTo(String value) {
            addCriterion("PUBLISH_USER_ID =", value, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdNotEqualTo(String value) {
            addCriterion("PUBLISH_USER_ID <>", value, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdGreaterThan(String value) {
            addCriterion("PUBLISH_USER_ID >", value, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_USER_ID >=", value, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdLessThan(String value) {
            addCriterion("PUBLISH_USER_ID <", value, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_USER_ID <=", value, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdLike(String value) {
            addCriterion("PUBLISH_USER_ID like", value, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdNotLike(String value) {
            addCriterion("PUBLISH_USER_ID not like", value, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdIn(List<String> values) {
            addCriterion("PUBLISH_USER_ID in", values, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdNotIn(List<String> values) {
            addCriterion("PUBLISH_USER_ID not in", values, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdBetween(String value1, String value2) {
            addCriterion("PUBLISH_USER_ID between", value1, value2, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andPublishUserIdNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_USER_ID not between", value1, value2, "publishUserId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
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

        public Criteria andParentCommentIdIsNull() {
            addCriterion("PARENT_COMMENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdIsNotNull() {
            addCriterion("PARENT_COMMENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdEqualTo(String value) {
            addCriterion("PARENT_COMMENT_ID =", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdNotEqualTo(String value) {
            addCriterion("PARENT_COMMENT_ID <>", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdGreaterThan(String value) {
            addCriterion("PARENT_COMMENT_ID >", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_COMMENT_ID >=", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdLessThan(String value) {
            addCriterion("PARENT_COMMENT_ID <", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_COMMENT_ID <=", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdLike(String value) {
            addCriterion("PARENT_COMMENT_ID like", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdNotLike(String value) {
            addCriterion("PARENT_COMMENT_ID not like", value, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdIn(List<String> values) {
            addCriterion("PARENT_COMMENT_ID in", values, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdNotIn(List<String> values) {
            addCriterion("PARENT_COMMENT_ID not in", values, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdBetween(String value1, String value2) {
            addCriterion("PARENT_COMMENT_ID between", value1, value2, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentCommentIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_COMMENT_ID not between", value1, value2, "parentCommentId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdIsNull() {
            addCriterion("PARENT_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentUserIdIsNotNull() {
            addCriterion("PARENT_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentUserIdEqualTo(String value) {
            addCriterion("PARENT_USER_ID =", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdNotEqualTo(String value) {
            addCriterion("PARENT_USER_ID <>", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdGreaterThan(String value) {
            addCriterion("PARENT_USER_ID >", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_USER_ID >=", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdLessThan(String value) {
            addCriterion("PARENT_USER_ID <", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_USER_ID <=", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdLike(String value) {
            addCriterion("PARENT_USER_ID like", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdNotLike(String value) {
            addCriterion("PARENT_USER_ID not like", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdIn(List<String> values) {
            addCriterion("PARENT_USER_ID in", values, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdNotIn(List<String> values) {
            addCriterion("PARENT_USER_ID not in", values, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdBetween(String value1, String value2) {
            addCriterion("PARENT_USER_ID between", value1, value2, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_USER_ID not between", value1, value2, "parentUserId");
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