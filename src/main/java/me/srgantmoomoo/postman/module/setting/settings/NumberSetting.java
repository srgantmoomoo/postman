package me.srgantmoomoo.postman.module.setting.settings;

import com.lukflug.panelstudio.setting.INumberSetting;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;

public class NumberSetting extends Setting implements INumberSetting {
    private double value;
    private double minimum;
    private double maximum;
    private double increment;

    public NumberSetting(String name, Module parent, double value, double minimum, double maximum, double increment) {
        setName(name);
        setParent(parent);
        this.value = value;
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = increment;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        double percision = 1.0D / increment;
        this.value = Math.round(Math.max(this.minimum, Math.min(this.maximum, value)) * percision) / percision;
    }

    public void increment(boolean positive) {
        setValue(getValue() + (positive ? 1 : -1) * increment);
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getIncrement() {
        return this.increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

    @Override
    public double getNumber() {
        return getValue();
    }

    @Override
    public void setNumber (double value) {
        setValue(value);
    }

    @Override
    public double getMaximumValue() {
        return getMaximum();
    }

    @Override
    public double getMinimumValue() {
        return getMinimum();
    }

    @Override
    public int getPrecision() {
        return 2;
    }
}
