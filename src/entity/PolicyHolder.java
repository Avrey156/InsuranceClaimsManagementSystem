package entity;

import utils.StringUtils;

import java.util.List;

public class PolicyHolder extends Customer {
    private List<String> dependentIds;

    public PolicyHolder(String id, String fullName, List<String> dependentIds) {
        super(id, fullName);
        this.dependentIds = dependentIds;
    }

    public List<String> getDependentIds() {
        return dependentIds;
    }

    public void setDependentIds(List<String> dependentIds) {
        this.dependentIds = dependentIds;
    }

    @Override
    public String toString() {
        return id + "," + fullName + "," + StringUtils.splitByDash(dependentIds);
    }
}
