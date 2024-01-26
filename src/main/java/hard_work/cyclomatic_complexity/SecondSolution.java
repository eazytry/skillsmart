package hard_work.cyclomatic_complexity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

public class SecondSolution {
    private OrderHandlerResolver orderHandlerResolver;

    // Выбор в зависимости от типа возвращает обработчик для нужного продукта.
    final class OrderHandlerResolver {
        public OrderHandler resolveOrderHandler(String orderType) {
            return switch (ProductType.fromString(orderType)) {
                // здесь реализация для фурнитуры
                case FURNITURE -> null;
                case ELECTRONICS -> new ElectronicsOrderHandler();
                default -> throw new UnsupportedOperationException();
            };
        }
    }

    // Базовый класс-обработчик заказа
    abstract class OrderHandler {
        // При желании можно сделать final чтобы жестко ограничить возможность переопределения,
        // но исходя из специфики подумал что это может быть полезно.
        public OrderStatus handle(Order order) {
            var handlersMap = Stream.concat(
                            defaultHandlersMapProvider(order).entrySet().stream(),
                            getHandlersMap(order).entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2));

            return ofNullable(handlersMap.get(order.getStatus()))
                    .map(List::stream)
                    .flatMap(stream -> stream.map(func -> func.apply(order)).findFirst())
                    .orElseThrow(() -> new UnsupportedOperationException());
        }

        protected abstract Map<OrderStatus, List<Function<Order, OrderStatus>>> getHandlersMap(Order order);

        private Map<OrderStatus, List<Function<Order, OrderStatus>>> defaultHandlersMapProvider(Order order) {
            var handlersList = Stream.of(
                            Optional.of(deliveryHandler(order)),
                            Optional.of(order)
                                    .filter(o -> ShipType.INTERNATIONAL.equals(o.getShipType()))
                                    .map(o -> internationalOrderHandler()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            return Map.of(OrderStatus.DISPATCH_READY, handlersList);
        }

        private Function<Order, OrderStatus> deliveryHandler(Order order) {
            return order.getSize() > 50 ? largeOrderDeliveryHandler() : smallOrderDeliveryHandler();
        }

        private Function<Order, OrderStatus> largeOrderDeliveryHandler() {
            // Larger order delivery logic here include special handling type
            return order -> OrderStatus.DELIVERED;
        }

        private Function<Order, OrderStatus> internationalOrderHandler() {
            // International delivery order handler
            return order -> OrderStatus.DELIVERED;
        }

        private Function<Order, OrderStatus> smallOrderDeliveryHandler() {
            // Smaller order delivery logic here include special handling type
            return order -> OrderStatus.DELIVERED;
        }
    }

    // Класс закрытый для изменений
    final class ElectronicsOrderHandler extends OrderHandler {
        @Override
        protected Map<OrderStatus, List<Function<Order, OrderStatus>>> getHandlersMap(Order order) {
            return Map.of(
                    OrderStatus.INITIAL, List.of(assemblyHandler()),
                    // Провайдер можно сделать зависимостью, но для наглядности написал тут
                    OrderStatus.ASSEMBLY_DONE, List.of(checkQualityHandlerProvider(order)),
                    OrderStatus.QUALITY_CHECK_PASSED, List.of(packageMaterialHandler()),
                    OrderStatus.PACKAGING_MATERIAL_AVAILABLE, List.of(dispatchHandler())
            );
        }

        private Function<Order, OrderStatus> assemblyHandler() {
            // Assembly logic
            return order -> OrderStatus.ASSEMBLY_DONE;
        }

        private Function<Order, OrderStatus> checkQualityHandlerProvider(Order order) {
            return order.getTemperature() > 30 ? specialCheckQualityHandler() : regularCheckQualityHandler();
        }

        private Function<Order, OrderStatus> specialCheckQualityHandler() {
            // quality check logic here
            return order -> OrderStatus.QUALITY_CHECK_PASSED;
        }

        private Function<Order, OrderStatus> regularCheckQualityHandler() {
            // quality check logic here
            return order -> OrderStatus.QUALITY_CHECK_PASSED;
        }

        private Function<Order, OrderStatus> packageMaterialHandler() {
            // Handle Packaging Material Shortage logic here
            return order -> OrderStatus.PACKAGING_MATERIAL_AVAILABLE;
        }

        private Function<Order, OrderStatus> dispatchHandler() {
            // Init logic here
            return order -> OrderStatus.ASSEMBLY_DONE;
        }
    }

    class Order {
        private final OrderStatus status;
        private final PackagingType packagingType;
        private final ShipType shipType;
        private final String type;
        private final int size;
        private final int temperature;

        public Order(OrderStatus status, PackagingType packagingType, ShipType shipType, String type, int size, int temperature) {
            this.status = status;
            this.packagingType = packagingType;
            this.shipType = shipType;
            this.type = type;
            this.size = size;
            this.temperature = temperature;
        }

        public OrderStatus getStatus() {
            return status;
        }

        public PackagingType getPackagingType() {
            return packagingType;
        }

        public ShipType getShipType() {
            return shipType;
        }

        public String getType() {
            return type;
        }

        public int getSize() {
            return size;
        }

        public int getTemperature() {
            return temperature;
        }
    }

    enum ProductType {
        ELECTRONICS("Electronics"),
        FURNITURE("Furniture"),
        UNKNOWN("UNKNOWN");

        private final String name;

        ProductType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static ProductType fromString(String name) {
            return Arrays.stream(ProductType.values())
                    .filter(n -> n.name.equals(name))
                    .findFirst()
                    .orElse(UNKNOWN);
        }
    }

    enum PackagingType {
        SPECIAL,
        PLAIN
    }

    enum ShipType {
        INTERNATIONAL,
        DOMESTIC
    }

    enum OrderStatus {
        INITIAL,
        ASSEMBLY_DONE,
        QUALITY_CHECK_PASSED,
        PACKAGING_MATERIAL_AVAILABLE,
        DISPATCH_READY,
        DELIVERED
    }


    public OrderStatus processOrder(Order order) {
        return orderHandlerResolver.resolveOrderHandler(order.getType()).handle(order);
    }
}
