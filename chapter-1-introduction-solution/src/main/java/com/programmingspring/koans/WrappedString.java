package com.programmingspring.koans;

public class WrappedString {

    private String wrappedString;

    public void setWrappedString(String wrappedString) {
        this.wrappedString = wrappedString;
    }

    public String getWrappedString() {
        return wrappedString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WrappedString that = (WrappedString) o;

        return !(wrappedString != null ? !wrappedString.equals(that.wrappedString) : that.wrappedString != null);

    }

    @Override
    public int hashCode() {
        return wrappedString != null ? wrappedString.hashCode() : 0;
    }
}
