package io.jenkins.plugins.coverage.model;


import org.jenkinsci.plugins.scriptsecurity.sandbox.whitelists.Whitelisted;
import hudson.model.BallColor;
import hudson.model.Result;

/**
 * Result of a QualityGateEvaluator#evaluate(SortedMap, FormattedLogger) call.
 *
 * @author Ullrich Hafner
 */
public enum QualityGateStatus {
    /** Quality gate is inactive, so result evaluation is not available. */
    INACTIVE(Result.NOT_BUILT),

    /** Quality gate has been passed. */
    PASSED(Result.SUCCESS),

    /** Quality gate has been missed: severity is a warning. */
    WARNING(Result.UNSTABLE),

    /** Quality gate has been missed: severity is an error. */
    FAILED(Result.FAILURE);

    private final Result result;

    QualityGateStatus(final Result result) {
        this.result = result;
    }

    /**
     * Returns the associated {@link Result} color.
     *
     * @return Jenkins' {@link Result} color
     */
    public BallColor getColor() {
        return result.color;
    }

    /**
     * Returns the associated {@link Result} icon class to be used in the UI.
     *
     * @return Jenkins' {@link Result} icon class
     */
    public String getIconClass() {
        return getColor().getIconClassName();
    }

    /**
     * Returns whether the quality gate has been passed (or has not been activated at all).
     *
     * @return {@code true} if the quality gate has been passed, {@code false}  otherwise
     */
    @Whitelisted
    public boolean isSuccessful() {
        return this == PASSED || this == INACTIVE;
    }

    /**
     * Returns the associated {@link Result}.
     *
     * @return the associated {@link Result}
     */
    public Result getResult() {
        return result;
    }

    /**
     * Returns whether this status is worse than the specified status.
     *
     * @param other
     *         the other status
     *
     * @return {@code true} if this status is worse than the other status, {@code false} otherwise
     */
    public boolean isWorseThan(final QualityGateStatus other) {
        return ordinal() > other.ordinal();
    }
}