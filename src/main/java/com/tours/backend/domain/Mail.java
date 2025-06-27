package com.tours.backend.domain;

public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

    private Mail(Builder builder) {
        this.mailTo = builder.mailTo;
        this.subject = builder.subject;
        this.message = builder.message;
        this.toCc = builder.toCc;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getToCc() {
        return toCc;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String mailTo;
        private String subject;
        private String message;
        private String toCc;

        public Builder mailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder toCc(String toCc) {
            this.toCc = toCc;
            return this;
        }

        public Mail build() {
            return new Mail(this);
        }
    }
}
