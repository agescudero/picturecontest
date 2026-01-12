package com.application.picturecontest.domain.model.valueobject;

public enum ContestLevel {

    FREE {
        @Override
        public boolean canSubmitTo(ContestLevel categoryLevel) {
            return categoryLevel == FREE;
        }
    },
    PREMIUM {
        @Override
        public boolean canSubmitTo(ContestLevel categoryLevel) {
            return categoryLevel ==PREMIUM || categoryLevel ==FREE;
        }
    };

    public abstract boolean canSubmitTo(ContestLevel categoryLevel);
}