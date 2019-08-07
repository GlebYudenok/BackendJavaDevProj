package by.gyudenok.java.se.task1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestApplication {

    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.print();
        Parent child = new Child();
        child.print();
    }

    static class Parent{
        protected String fio;

        public Parent(){
            fio = new String("анна мария оглы");
        }

        private void print(){
            String s = new String();
            if (this.equals(new Child())){
                s = change(fio);
            }else {
                s = fio + '!';
            }

            System.out.println(s);
        }


        /**
         * Function splits string in array, then insert symbols.
         * @param fio field, which need to change
         * @return changed field
         */
        private String change(String fio){

            fio = fio.toLowerCase();
            List<String> upperWords =
                    Stream.of(fio.split("[\\P{L}]+"))
                            .map(w ->w.substring(0,1)
                                    .toUpperCase() + w
                                    .substring(1))
                            .collect(Collectors.toList());

            StringBuilder tmp = new StringBuilder();
            for (String s : upperWords){
                tmp.append(s);
            }

            char []a = {'\'', '-', ' '};
            for (int j = 0; j < a.length; j++) {
                int index = fio.indexOf(a[j]);
                if (index != -1) {
                    tmp.insert(index, a[j]);
                }
            }
            return tmp.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Parent parent = (Parent) o;

            return fio != null ? fio.equals(parent.fio) : parent.fio == null;

        }

        @Override
        public int hashCode() {
            return fio != null ? fio.hashCode() : 0;
        }
    }

    static class Child extends Parent{
        public Child(){
            super();
            fio = new String("АН'НА-МАРИЯ оглы");
        }
    }
}
