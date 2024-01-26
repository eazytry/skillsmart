package hard_work.cyclomatic_complexity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

public class SecondTask {

    public void processProduct(String productType, boolean assemblyDone, boolean qualityCheckPassed,
                               boolean isPackagingMaterialAvailable, boolean isDispatchReady,
                               int productSize, int temperature, int humidity,
                               boolean specialHandlingRequired, boolean isInternationalOrder) {
        if (productType.equals("Electronics")) {
            if (assemblyDone) {
                if (qualityCheckPassed) {
                    if (isPackagingMaterialAvailable) {
                        // Packaging Logic
                    } else {
                        // Handle Packaging Material Shortage
                    }
                } else {
                    if (temperature > 30) {
                        // Special Quality Check for High Temperature
                    } else {
                        // Regular Quality Check Failure Handling
                    }
                }
            } else {
                // Assembly Logic
            }
        } else if (productType.equals("Furniture")) {
            // Similar nested conditions for Furniture
        }
        // ... more product types with similar nested conditions

        // Additional checks for dispatch
        if (isDispatchReady) {
            if (productSize > 50) {
                if (specialHandlingRequired) {
                    // Logic for Large Products with Special Handling
                } else {
                    // Logic for Large Products without Special Handling
                }
            } else {
                // Logic for Smaller Products
            }
            if (isInternationalOrder) {
                // Additional Steps for International Orders
            }
        }
        // ... other conditions and logic
    }
}