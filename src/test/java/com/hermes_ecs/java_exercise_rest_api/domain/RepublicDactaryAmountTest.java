package com.hermes_ecs.java_exercise_rest_api.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RepublicDactaryAmountTest {

    @Test
    public void isValidWithNullValueReturnsFalse() {
        assertThat(RepublicDactaryAmount.isValid(null), is(false));
    }

    @Test
    public void isValidWithEmptyValueReturnsFalse() {
        assertThat("Valid value for empty string",
                RepublicDactaryAmount.isValid(""), is(false));
        assertThat("Valid value for not numeric string",
                RepublicDactaryAmount.isValid("wtf"), is(false));
    }

    @Test
    public void isValidWithValueWithSymbolReturnsTrue() {
        assertThat(
                RepublicDactaryAmount.isValid("5 "
                        + RepublicDactaryAmount.SYMBOL), is(true));
    }

    @Test
    public void isValidWithValueWithWrongSymbolReturnsFalse() {
        assertThat(RepublicDactaryAmount.isValid("5,15 euros"), is(false));
    }

    @Test
    public void isValidWithDecimalValueAndCommaReturnsTrue() {
        assertThat(RepublicDactaryAmount.isValid("5,15"), is(true));
    }

    @Test
    public void isValidWithDecimalValueAndDotReturnsTrue() {
        assertThat(RepublicDactaryAmount.isValid("5.15"), is(true));
    }

    @Test
    public void isValidWithValuesSurroundedByBlanksReturnsTrue() {
        assertThat(RepublicDactaryAmount.isValid("   6   "), is(true));
    }

    @Test
    public void constructorWithCurrencySymbolKeepsValue() {
        assertThat(new RepublicDactaryAmount("7 "
                + RepublicDactaryAmount.SYMBOL).getValue(), is(equalTo(7f)));
        assertThat(
                new RepublicDactaryAmount("7" + RepublicDactaryAmount.SYMBOL)
                        .getValue(),
                is(equalTo(7f)));
    }

    @Test
    public void constructorWithDecimalValueWithCommaKeepsValue() {
        assertThat(new RepublicDactaryAmount("7,15 "
                + RepublicDactaryAmount.SYMBOL).getValue(), is(equalTo(7.15f)));
        assertThat(new RepublicDactaryAmount("7,15"
                + RepublicDactaryAmount.SYMBOL).getValue(), is(equalTo(7.15f)));
    }

    @Test
    public void constructorWithDecimalValueWithDotKeepsValue() {
        assertThat(new RepublicDactaryAmount("7.15 "
                + RepublicDactaryAmount.SYMBOL).getValue(), is(equalTo(7.15f)));
        assertThat(new RepublicDactaryAmount("7.15"
                + RepublicDactaryAmount.SYMBOL).getValue(), is(equalTo(7.15f)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullValueThrowsAnException() {
        new RepublicDactaryAmount(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithEmptyValueThrowsAnException() {
        new RepublicDactaryAmount("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithWrongSymbolThrowsAnException() {
        new RepublicDactaryAmount("5,15 euros");
    }
}
