package com.koant.sonar.slacknotifier.common.component;

import com.koant.sonar.slacknotifier.common.SlackNotifierProp;
import org.sonar.api.config.Settings;

import java.util.Objects;

/**
 * Created by ak on 17/10/16.
 * Modified by poznachowski
 */
public class ProjectConfig {
    private final String projectKey;
    private final String slackChannel;
    private final String notify;
    private final boolean qgFailOnly;

    public ProjectConfig(String projectKey, String slackChannel, String notify, boolean qgFailOnly) {
        this.projectKey = projectKey;
        this.slackChannel = slackChannel;
        this.notify = notify;
        this.qgFailOnly = qgFailOnly;
    }

    /**
     * Cloning constructor
     *
     * @param c
     */
    public ProjectConfig(ProjectConfig c) {
        this.projectKey = c.getProjectKey();
        this.slackChannel = c.getSlackChannel();
        this.qgFailOnly = c.isQgFailOnly();
        this.notify = c.getNotify();
    }

    static ProjectConfig create(Settings settings, String configurationId) {
        String configurationPrefix = SlackNotifierProp.CONFIG.property() + "." + configurationId + ".";
        String projectKey = settings.getString(configurationPrefix + SlackNotifierProp.PROJECT.property());
        String slackChannel = settings.getString(configurationPrefix + SlackNotifierProp.CHANNEL.property());
        String notify = settings.getString(configurationPrefix+SlackNotifierProp.NOTIFY.property());
        boolean qgFailOnly = settings.getBoolean(configurationPrefix + SlackNotifierProp.QG_FAIL_ONLY.property());
        return new ProjectConfig(projectKey, slackChannel, notify, qgFailOnly);
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getSlackChannel() {
        return slackChannel;
    }

    public boolean isQgFailOnly() {
        return qgFailOnly;
    }

    public String getNotify() {return notify;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectConfig that = (ProjectConfig) o;
        return qgFailOnly == that.qgFailOnly &&
                Objects.equals(projectKey, that.projectKey) &&
                Objects.equals(notify, that.notify) &&
                Objects.equals(slackChannel, that.slackChannel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectKey, slackChannel, qgFailOnly);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectConfig{");
        sb.append("projectKey='").append(projectKey).append('\'');
        sb.append(", slackChannel='").append(slackChannel).append('\'');
        sb.append(", notify='").append(notify).append('\'');
        sb.append(", qgFailOnly=").append(qgFailOnly);
        sb.append('}');
        return sb.toString();
    }
}
