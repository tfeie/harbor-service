package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyGoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyGoCriteria() {
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

        public Criteria andGoTypeIsNull() {
            addCriterion("GO_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGoTypeIsNotNull() {
            addCriterion("GO_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGoTypeEqualTo(String value) {
            addCriterion("GO_TYPE =", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeNotEqualTo(String value) {
            addCriterion("GO_TYPE <>", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeGreaterThan(String value) {
            addCriterion("GO_TYPE >", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GO_TYPE >=", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeLessThan(String value) {
            addCriterion("GO_TYPE <", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeLessThanOrEqualTo(String value) {
            addCriterion("GO_TYPE <=", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeLike(String value) {
            addCriterion("GO_TYPE like", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeNotLike(String value) {
            addCriterion("GO_TYPE not like", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeIn(List<String> values) {
            addCriterion("GO_TYPE in", values, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeNotIn(List<String> values) {
            addCriterion("GO_TYPE not in", values, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeBetween(String value1, String value2) {
            addCriterion("GO_TYPE between", value1, value2, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeNotBetween(String value1, String value2) {
            addCriterion("GO_TYPE not between", value1, value2, "goType");
            return (Criteria) this;
        }

        public Criteria andTopicIsNull() {
            addCriterion("TOPIC is null");
            return (Criteria) this;
        }

        public Criteria andTopicIsNotNull() {
            addCriterion("TOPIC is not null");
            return (Criteria) this;
        }

        public Criteria andTopicEqualTo(String value) {
            addCriterion("TOPIC =", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotEqualTo(String value) {
            addCriterion("TOPIC <>", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicGreaterThan(String value) {
            addCriterion("TOPIC >", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicGreaterThanOrEqualTo(String value) {
            addCriterion("TOPIC >=", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicLessThan(String value) {
            addCriterion("TOPIC <", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicLessThanOrEqualTo(String value) {
            addCriterion("TOPIC <=", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicLike(String value) {
            addCriterion("TOPIC like", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotLike(String value) {
            addCriterion("TOPIC not like", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicIn(List<String> values) {
            addCriterion("TOPIC in", values, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotIn(List<String> values) {
            addCriterion("TOPIC not in", values, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicBetween(String value1, String value2) {
            addCriterion("TOPIC between", value1, value2, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotBetween(String value1, String value2) {
            addCriterion("TOPIC not between", value1, value2, "topic");
            return (Criteria) this;
        }

        public Criteria andInviteMembersIsNull() {
            addCriterion("INVITE_MEMBERS is null");
            return (Criteria) this;
        }

        public Criteria andInviteMembersIsNotNull() {
            addCriterion("INVITE_MEMBERS is not null");
            return (Criteria) this;
        }

        public Criteria andInviteMembersEqualTo(String value) {
            addCriterion("INVITE_MEMBERS =", value, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersNotEqualTo(String value) {
            addCriterion("INVITE_MEMBERS <>", value, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersGreaterThan(String value) {
            addCriterion("INVITE_MEMBERS >", value, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersGreaterThanOrEqualTo(String value) {
            addCriterion("INVITE_MEMBERS >=", value, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersLessThan(String value) {
            addCriterion("INVITE_MEMBERS <", value, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersLessThanOrEqualTo(String value) {
            addCriterion("INVITE_MEMBERS <=", value, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersLike(String value) {
            addCriterion("INVITE_MEMBERS like", value, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersNotLike(String value) {
            addCriterion("INVITE_MEMBERS not like", value, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersIn(List<String> values) {
            addCriterion("INVITE_MEMBERS in", values, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersNotIn(List<String> values) {
            addCriterion("INVITE_MEMBERS not in", values, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersBetween(String value1, String value2) {
            addCriterion("INVITE_MEMBERS between", value1, value2, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andInviteMembersNotBetween(String value1, String value2) {
            addCriterion("INVITE_MEMBERS not between", value1, value2, "inviteMembers");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeIsNull() {
            addCriterion("EXPECTED_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeIsNotNull() {
            addCriterion("EXPECTED_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeEqualTo(String value) {
            addCriterion("EXPECTED_START_TIME =", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeNotEqualTo(String value) {
            addCriterion("EXPECTED_START_TIME <>", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeGreaterThan(String value) {
            addCriterion("EXPECTED_START_TIME >", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXPECTED_START_TIME >=", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeLessThan(String value) {
            addCriterion("EXPECTED_START_TIME <", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeLessThanOrEqualTo(String value) {
            addCriterion("EXPECTED_START_TIME <=", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeLike(String value) {
            addCriterion("EXPECTED_START_TIME like", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeNotLike(String value) {
            addCriterion("EXPECTED_START_TIME not like", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeIn(List<String> values) {
            addCriterion("EXPECTED_START_TIME in", values, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeNotIn(List<String> values) {
            addCriterion("EXPECTED_START_TIME not in", values, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeBetween(String value1, String value2) {
            addCriterion("EXPECTED_START_TIME between", value1, value2, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeNotBetween(String value1, String value2) {
            addCriterion("EXPECTED_START_TIME not between", value1, value2, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationIsNull() {
            addCriterion("EXPECTED_DURATION is null");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationIsNotNull() {
            addCriterion("EXPECTED_DURATION is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationEqualTo(String value) {
            addCriterion("EXPECTED_DURATION =", value, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationNotEqualTo(String value) {
            addCriterion("EXPECTED_DURATION <>", value, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationGreaterThan(String value) {
            addCriterion("EXPECTED_DURATION >", value, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationGreaterThanOrEqualTo(String value) {
            addCriterion("EXPECTED_DURATION >=", value, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationLessThan(String value) {
            addCriterion("EXPECTED_DURATION <", value, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationLessThanOrEqualTo(String value) {
            addCriterion("EXPECTED_DURATION <=", value, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationLike(String value) {
            addCriterion("EXPECTED_DURATION like", value, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationNotLike(String value) {
            addCriterion("EXPECTED_DURATION not like", value, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationIn(List<String> values) {
            addCriterion("EXPECTED_DURATION in", values, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationNotIn(List<String> values) {
            addCriterion("EXPECTED_DURATION not in", values, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationBetween(String value1, String value2) {
            addCriterion("EXPECTED_DURATION between", value1, value2, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andExpectedDurationNotBetween(String value1, String value2) {
            addCriterion("EXPECTED_DURATION not between", value1, value2, "expectedDuration");
            return (Criteria) this;
        }

        public Criteria andPayModeIsNull() {
            addCriterion("PAY_MODE is null");
            return (Criteria) this;
        }

        public Criteria andPayModeIsNotNull() {
            addCriterion("PAY_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andPayModeEqualTo(String value) {
            addCriterion("PAY_MODE =", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotEqualTo(String value) {
            addCriterion("PAY_MODE <>", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeGreaterThan(String value) {
            addCriterion("PAY_MODE >", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_MODE >=", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeLessThan(String value) {
            addCriterion("PAY_MODE <", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeLessThanOrEqualTo(String value) {
            addCriterion("PAY_MODE <=", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeLike(String value) {
            addCriterion("PAY_MODE like", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotLike(String value) {
            addCriterion("PAY_MODE not like", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeIn(List<String> values) {
            addCriterion("PAY_MODE in", values, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotIn(List<String> values) {
            addCriterion("PAY_MODE not in", values, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeBetween(String value1, String value2) {
            addCriterion("PAY_MODE between", value1, value2, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotBetween(String value1, String value2) {
            addCriterion("PAY_MODE not between", value1, value2, "payMode");
            return (Criteria) this;
        }

        public Criteria andFixedPriceIsNull() {
            addCriterion("FIXED_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andFixedPriceIsNotNull() {
            addCriterion("FIXED_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andFixedPriceEqualTo(long value) {
            addCriterion("FIXED_PRICE =", value, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andFixedPriceNotEqualTo(long value) {
            addCriterion("FIXED_PRICE <>", value, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andFixedPriceGreaterThan(long value) {
            addCriterion("FIXED_PRICE >", value, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andFixedPriceGreaterThanOrEqualTo(long value) {
            addCriterion("FIXED_PRICE >=", value, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andFixedPriceLessThan(long value) {
            addCriterion("FIXED_PRICE <", value, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andFixedPriceLessThanOrEqualTo(long value) {
            addCriterion("FIXED_PRICE <=", value, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andFixedPriceIn(List<Long> values) {
            addCriterion("FIXED_PRICE in", values, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andFixedPriceNotIn(List<Long> values) {
            addCriterion("FIXED_PRICE not in", values, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andFixedPriceBetween(long value1, long value2) {
            addCriterion("FIXED_PRICE between", value1, value2, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andFixedPriceNotBetween(long value1, long value2) {
            addCriterion("FIXED_PRICE not between", value1, value2, "fixedPrice");
            return (Criteria) this;
        }

        public Criteria andOrgModeIsNull() {
            addCriterion("ORG_MODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgModeIsNotNull() {
            addCriterion("ORG_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgModeEqualTo(String value) {
            addCriterion("ORG_MODE =", value, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeNotEqualTo(String value) {
            addCriterion("ORG_MODE <>", value, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeGreaterThan(String value) {
            addCriterion("ORG_MODE >", value, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_MODE >=", value, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeLessThan(String value) {
            addCriterion("ORG_MODE <", value, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeLessThanOrEqualTo(String value) {
            addCriterion("ORG_MODE <=", value, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeLike(String value) {
            addCriterion("ORG_MODE like", value, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeNotLike(String value) {
            addCriterion("ORG_MODE not like", value, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeIn(List<String> values) {
            addCriterion("ORG_MODE in", values, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeNotIn(List<String> values) {
            addCriterion("ORG_MODE not in", values, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeBetween(String value1, String value2) {
            addCriterion("ORG_MODE between", value1, value2, "orgMode");
            return (Criteria) this;
        }

        public Criteria andOrgModeNotBetween(String value1, String value2) {
            addCriterion("ORG_MODE not between", value1, value2, "orgMode");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("LOCATION is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("LOCATION is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("LOCATION =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("LOCATION <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("LOCATION >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("LOCATION >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("LOCATION <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("LOCATION <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("LOCATION like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("LOCATION not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("LOCATION in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("LOCATION not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("LOCATION between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("LOCATION not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andMyStoryIsNull() {
            addCriterion("MY_STORY is null");
            return (Criteria) this;
        }

        public Criteria andMyStoryIsNotNull() {
            addCriterion("MY_STORY is not null");
            return (Criteria) this;
        }

        public Criteria andMyStoryEqualTo(String value) {
            addCriterion("MY_STORY =", value, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryNotEqualTo(String value) {
            addCriterion("MY_STORY <>", value, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryGreaterThan(String value) {
            addCriterion("MY_STORY >", value, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryGreaterThanOrEqualTo(String value) {
            addCriterion("MY_STORY >=", value, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryLessThan(String value) {
            addCriterion("MY_STORY <", value, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryLessThanOrEqualTo(String value) {
            addCriterion("MY_STORY <=", value, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryLike(String value) {
            addCriterion("MY_STORY like", value, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryNotLike(String value) {
            addCriterion("MY_STORY not like", value, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryIn(List<String> values) {
            addCriterion("MY_STORY in", values, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryNotIn(List<String> values) {
            addCriterion("MY_STORY not in", values, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryBetween(String value1, String value2) {
            addCriterion("MY_STORY between", value1, value2, "myStory");
            return (Criteria) this;
        }

        public Criteria andMyStoryNotBetween(String value1, String value2) {
            addCriterion("MY_STORY not between", value1, value2, "myStory");
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

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Timestamp value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Timestamp value) {
            addCriterion("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Timestamp value) {
            addCriterion("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Timestamp value) {
            addCriterion("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Timestamp> values) {
            addCriterion("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Timestamp> values) {
            addCriterion("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
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