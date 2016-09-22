package bio.informatica.udistrital.controller;

/**
 * Created by hacortesn on 22/09/16.
 */

import bio.informatica.udistrital.logic.Matrix;
import bio.informatica.udistrital.logic.NitrogenousBase;
import bio.informatica.udistrital.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping("/matrix")
    public Map<String, Object> matrix() {

        Matrix m;
        List<NitrogenousBase> sequence1 = Arrays.asList(
                NitrogenousBase.A,
                NitrogenousBase.C,
                NitrogenousBase.C,
                NitrogenousBase.G,
                NitrogenousBase.T,
                NitrogenousBase.C,
                NitrogenousBase.T,
                NitrogenousBase.T
        );

        List<NitrogenousBase> sequence2 = Arrays.asList(
                NitrogenousBase.C,
                NitrogenousBase.G,
                NitrogenousBase.T,
                NitrogenousBase.C,
                NitrogenousBase.T,
                NitrogenousBase.T
        );

        m = new Matrix(sequence2, sequence1);

        int r = m.getRows();
        int c = m.getColumns();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(m.getValueIn(i, j) + "\t");
            }
            System.out.println("");
        }

        System.out.println("final score" + m.getScore());
        Map<String, Object> value = new HashMap<>();
        value.put("matrix", m.getMatrix());
        value.put("alignment", m.getPathWay());

        return value;
    }

}