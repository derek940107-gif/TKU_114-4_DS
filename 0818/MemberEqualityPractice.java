public class MemberEqualityPractice {
    public static void main(String[] args) {
        LibraryMember member1 = new LibraryMember(
                "M001", "王小明", "ming@gmail.com"
        );

        LibraryMember member2 = new LibraryMember(
                "M001", "王小明", "ming123@gmail.com"
        );

        System.out.println(member1);
        System.out.println(member2);

        System.out.println("== 比較結果：" + (member1 == member2));
        System.out.println("equals() 比較結果：" + member1.equals(member2));

        System.out.println("與 null 比較：" + member1.equals(null));
    }
}

class LibraryMember {
    private String memberId;
    private String name;
    private String email;

    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "會員編號：" + memberId
                + "，姓名：" + name
                + "，Email：" + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        LibraryMember other = (LibraryMember) obj;
        return memberId.equals(other.memberId);
    }

    @Override
    public int hashCode() {
        return memberId.hashCode();
    }
}