public class GenericResultDemo {

    public static class Result<T> {
        private boolean success;
        private String message;
        private T data;

        private Result(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public static <T> Result<T> success(T data) {
            return new Result<>(true, "Success", data);
        }

        public static <T> Result<T> success(String message, T data) {
            return new Result<>(true, message, data);
        }

        public static <T> Result<T> failure(String message) {
            return new Result<>(false, message, null);
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "success=" + success +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    public static void main(String[] args) {
        Result<String> stringSuccess = Result.success("Hello World");
        Result<String> stringFailure = Result.failure("Network Timeout");

        Result<Integer> intSuccess = Result.success(100);
        Result<Integer> intFailure = Result.failure("Invalid Input");

        System.out.println(stringSuccess);
        System.out.println(stringFailure);
        System.out.println(intSuccess);
        System.out.println(intFailure);

        if (stringSuccess.isSuccess()) {
            String strData = stringSuccess.getData();
            System.out.println("Extracted String Data: " + strData);
        }

        if (!intFailure.isSuccess()) {
            Integer nullData = intFailure.getData();
            System.out.println("Failed Result Data: " + nullData);
        }
    }
}