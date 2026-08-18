public class EquipmentInventory {
    public static void main(String[] args) {
        Equipment equipment1 = new Equipment("E001", "Laptop", 2);
        Equipment equipment2 = new Equipment("", "", -1);

        System.out.println(equipment1);
        System.out.println(equipment2);

        System.out.println(equipment1.borrowOne());
        System.out.println(equipment1);
        
        System.out.println(equipment1.borrowOne());
        System.out.println(equipment1);
        
        System.out.println(equipment1.borrowOne());
        System.out.println(equipment1);

        equipment1.returnItems(3);
        System.out.println(equipment1);

        equipment2.returnItems(2);
        System.out.println(equipment2);
    }
}

class Equipment {
    private String id;
    private String name;
    private int availableCount;

    public Equipment(String id, String name, int availableCount) {
        this.id = id == null || id.trim().isEmpty() ? "Unknown" : id;
        this.name = name == null || name.trim().isEmpty() ? "Unknown" : name;
        this.availableCount = availableCount < 0 ? 0 : availableCount;
    }

    public boolean borrowOne() {
        if (availableCount > 0) {
            availableCount--;
            return true;
        }
        return false;
    }

    public void returnItems(int quantity) {
        if (quantity > 0) {
            availableCount += quantity;
        }
    }

    @Override
    public String toString() {
        return "設備編號：" + id + "，名稱：" + name + "，可借數：" + availableCount;
    }
}