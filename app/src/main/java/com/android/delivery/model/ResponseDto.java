package com.android.delivery.model;

public class ResponseDto<T> {
    public boolean isSuccess() {
        return success;
    }

    public T getResponse() {
        return response;
    }

    public ApiError getError() {
        return error;
    }

    private final boolean success;
        private final T response;
        private final ApiError error;


        public ResponseDto(boolean success, T response, ApiError error) {
            this.success = success;
            this.response = response;
            this.error = error;
        }

        public static class ApiError{
            private final String message;
            private int status;

            public ApiError(String message, int status ) {
                this.message = message;
                this.status = status;
            }

            public ApiError(String message) {
                this.message = message;
            }

            public String getMessage(){
                return message;
            }

            public  int getStatus(){
                return status;
            }

        }
}
