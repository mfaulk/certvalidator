package no.difi.virksomhetssertifikat.structure;

import no.difi.virksomhetssertifikat.api.ValidatorRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractJunction implements ValidatorRule {

    protected List<ValidatorRule> validatorRules = new ArrayList<ValidatorRule>();

    public AbstractJunction(ValidatorRule... validatorRules) {
        addRule(validatorRules);
    }

    public void addRule(ValidatorRule... validatorRules) {
        this.validatorRules.addAll(Arrays.asList(validatorRules));
    }

}