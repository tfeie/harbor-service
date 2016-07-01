package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyNotifyCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyNotifyCriteria() {
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

        public Criteria andNotifyIdIsNull() {
            addCriterion("NOTIFY_ID is null");
            return (Criteria) this;
        }

        public Criteria andNotifyIdIsNotNull() {
            addCriterion("NOTIFY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyIdEqualTo(String value) {
            addCriterion("NOTIFY_ID =", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdNotEqualTo(String value) {
            addCriterion("NOTIFY_ID <>", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdGreaterThan(String value) {
            addCriterion("NOTIFY_ID >", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdGreaterThanOrEqualTo(String value) {
            addCriterion("NOTIFY_ID >=", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdLessThan(String value) {
            addCriterion("NOTIFY_ID <", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdLessThanOrEqualTo(String value) {
            addCriterion("NOTIFY_ID <=", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdLike(String value) {
            addCriterion("NOTIFY_ID like", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdNotLike(String value) {
            addCriterion("NOTIFY_ID not like", value, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdIn(List<String> values) {
            addCriterion("NOTIFY_ID in", values, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdNotIn(List<String> values) {
            addCriterion("NOTIFY_ID not in", values, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdBetween(String value1, String value2) {
            addCriterion("NOTIFY_ID between", value1, value2, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyIdNotBetween(String value1, String value2) {
            addCriterion("NOTIFY_ID not between", value1, value2, "notifyId");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeIsNull() {
            addCriterion("NOTIFY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeIsNotNull() {
            addCriterion("NOTIFY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeEqualTo(String value) {
            addCriterion("NOTIFY_TYPE =", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeNotEqualTo(String value) {
            addCriterion("NOTIFY_TYPE <>", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeGreaterThan(String value) {
            addCriterion("NOTIFY_TYPE >", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("NOTIFY_TYPE >=", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeLessThan(String value) {
            addCriterion("NOTIFY_TYPE <", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeLessThanOrEqualTo(String value) {
            addCriterion("NOTIFY_TYPE <=", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeLike(String value) {
            addCriterion("NOTIFY_TYPE like", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeNotLike(String value) {
            addCriterion("NOTIFY_TYPE not like", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeIn(List<String> values) {
            addCriterion("NOTIFY_TYPE in", values, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeNotIn(List<String> values) {
            addCriterion("NOTIFY_TYPE not in", values, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeBetween(String value1, String value2) {
            addCriterion("NOTIFY_TYPE between", value1, value2, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeNotBetween(String value1, String value2) {
            addCriterion("NOTIFY_TYPE not between", value1, value2, "notifyType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeIsNull() {
            addCriterion("SENDER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSenderTypeIsNotNull() {
            addCriterion("SENDER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSenderTypeEqualTo(String value) {
            addCriterion("SENDER_TYPE =", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotEqualTo(String value) {
            addCriterion("SENDER_TYPE <>", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeGreaterThan(String value) {
            addCriterion("SENDER_TYPE >", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SENDER_TYPE >=", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeLessThan(String value) {
            addCriterion("SENDER_TYPE <", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeLessThanOrEqualTo(String value) {
            addCriterion("SENDER_TYPE <=", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeLike(String value) {
            addCriterion("SENDER_TYPE like", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotLike(String value) {
            addCriterion("SENDER_TYPE not like", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeIn(List<String> values) {
            addCriterion("SENDER_TYPE in", values, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotIn(List<String> values) {
            addCriterion("SENDER_TYPE not in", values, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeBetween(String value1, String value2) {
            addCriterion("SENDER_TYPE between", value1, value2, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotBetween(String value1, String value2) {
            addCriterion("SENDER_TYPE not between", value1, value2, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderIdIsNull() {
            addCriterion("SENDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSenderIdIsNotNull() {
            addCriterion("SENDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSenderIdEqualTo(String value) {
            addCriterion("SENDER_ID =", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotEqualTo(String value) {
            addCriterion("SENDER_ID <>", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThan(String value) {
            addCriterion("SENDER_ID >", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThanOrEqualTo(String value) {
            addCriterion("SENDER_ID >=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThan(String value) {
            addCriterion("SENDER_ID <", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThanOrEqualTo(String value) {
            addCriterion("SENDER_ID <=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLike(String value) {
            addCriterion("SENDER_ID like", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotLike(String value) {
            addCriterion("SENDER_ID not like", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdIn(List<String> values) {
            addCriterion("SENDER_ID in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotIn(List<String> values) {
            addCriterion("SENDER_ID not in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdBetween(String value1, String value2) {
            addCriterion("SENDER_ID between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotBetween(String value1, String value2) {
            addCriterion("SENDER_ID not between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeIsNull() {
            addCriterion("ACCEPTER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeIsNotNull() {
            addCriterion("ACCEPTER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeEqualTo(String value) {
            addCriterion("ACCEPTER_TYPE =", value, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeNotEqualTo(String value) {
            addCriterion("ACCEPTER_TYPE <>", value, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeGreaterThan(String value) {
            addCriterion("ACCEPTER_TYPE >", value, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPTER_TYPE >=", value, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeLessThan(String value) {
            addCriterion("ACCEPTER_TYPE <", value, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeLessThanOrEqualTo(String value) {
            addCriterion("ACCEPTER_TYPE <=", value, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeLike(String value) {
            addCriterion("ACCEPTER_TYPE like", value, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeNotLike(String value) {
            addCriterion("ACCEPTER_TYPE not like", value, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeIn(List<String> values) {
            addCriterion("ACCEPTER_TYPE in", values, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeNotIn(List<String> values) {
            addCriterion("ACCEPTER_TYPE not in", values, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeBetween(String value1, String value2) {
            addCriterion("ACCEPTER_TYPE between", value1, value2, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterTypeNotBetween(String value1, String value2) {
            addCriterion("ACCEPTER_TYPE not between", value1, value2, "accepterType");
            return (Criteria) this;
        }

        public Criteria andAccepterIdIsNull() {
            addCriterion("ACCEPTER_ID is null");
            return (Criteria) this;
        }

        public Criteria andAccepterIdIsNotNull() {
            addCriterion("ACCEPTER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAccepterIdEqualTo(String value) {
            addCriterion("ACCEPTER_ID =", value, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdNotEqualTo(String value) {
            addCriterion("ACCEPTER_ID <>", value, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdGreaterThan(String value) {
            addCriterion("ACCEPTER_ID >", value, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPTER_ID >=", value, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdLessThan(String value) {
            addCriterion("ACCEPTER_ID <", value, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdLessThanOrEqualTo(String value) {
            addCriterion("ACCEPTER_ID <=", value, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdLike(String value) {
            addCriterion("ACCEPTER_ID like", value, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdNotLike(String value) {
            addCriterion("ACCEPTER_ID not like", value, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdIn(List<String> values) {
            addCriterion("ACCEPTER_ID in", values, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdNotIn(List<String> values) {
            addCriterion("ACCEPTER_ID not in", values, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdBetween(String value1, String value2) {
            addCriterion("ACCEPTER_ID between", value1, value2, "accepterId");
            return (Criteria) this;
        }

        public Criteria andAccepterIdNotBetween(String value1, String value2) {
            addCriterion("ACCEPTER_ID not between", value1, value2, "accepterId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
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

        public Criteria andLinkIsNull() {
            addCriterion("LINK is null");
            return (Criteria) this;
        }

        public Criteria andLinkIsNotNull() {
            addCriterion("LINK is not null");
            return (Criteria) this;
        }

        public Criteria andLinkEqualTo(String value) {
            addCriterion("LINK =", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotEqualTo(String value) {
            addCriterion("LINK <>", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThan(String value) {
            addCriterion("LINK >", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThanOrEqualTo(String value) {
            addCriterion("LINK >=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThan(String value) {
            addCriterion("LINK <", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThanOrEqualTo(String value) {
            addCriterion("LINK <=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLike(String value) {
            addCriterion("LINK like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotLike(String value) {
            addCriterion("LINK not like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkIn(List<String> values) {
            addCriterion("LINK in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotIn(List<String> values) {
            addCriterion("LINK not in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkBetween(String value1, String value2) {
            addCriterion("LINK between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotBetween(String value1, String value2) {
            addCriterion("LINK not between", value1, value2, "link");
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