package com.vivek.truecaller.model.common;

import java.util.HashMap;
import java.util.Map;

public class SocialInfo {
    private Map<SocialProfileType, String> socialInfo = new HashMap<>();

    public Map<SocialProfileType, String> getSocialInfo() {
        return socialInfo;
    }

    public void setSocialInfo(Map<SocialProfileType, String> socialInfo) {
        this.socialInfo = socialInfo;
    }
}
