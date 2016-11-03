package bio.informatica.udistrital.controller;

/**
 * Created by hacortesn on 22/09/16.
 */

import bio.informatica.udistrital.logic.Matrix;
import bio.informatica.udistrital.logic.NitrogenousBase;
import bio.informatica.udistrital.model.Greeting;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }


    int counterSequeces = 0;

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping("/alignment")
    public Map<String, Object> matrix(@RequestParam("archivoFasta") MultipartFile file) throws IOException {
        File temp = null;

        temp = File.createTempFile("fasta", ".fasta");
        file.transferTo(temp);

        counterSequeces = 0;
        Map<String, List<NitrogenousBase>> sequences = new HashMap<>();
        List<String> names = new ArrayList<>();

        Path path = Paths.get(temp.toURI());
        List<String> lines = Files.readAllLines(path);
        lines.forEach(line -> {
            if (line.startsWith(">")) {
                names.add(counterSequeces, line.substring(1));
                counterSequeces++;

            } else {
                String name = names.get(counterSequeces - 1);
                if (!sequences.containsKey(name)) {
                    sequences.put(name, new ArrayList<>());
                }
                List<NitrogenousBase> before = sequences.get(name);
                List<String> ts = Arrays.asList(line.split(""));
                before.addAll(NitrogenousBase.getBases(ts));
                sequences.put(name, before);
            }
        });

        Matrix m;
        List<NitrogenousBase> sequence1 = Arrays.asList(
                NitrogenousBase.A,
                NitrogenousBase.C,
                NitrogenousBase.G
        );

        List<NitrogenousBase> sequence2 = Arrays.asList(
                NitrogenousBase.T

        );

        m = new Matrix(sequences.get(names.get(1)), sequences.get(names.get(0)));
//        m = new Matrix(sequence2, sequence1);


        Map<String, Object> value = new HashMap<>();
        value.put("matrix", m.getMatrix());
        value.put("alignment", m.getPathWay());
        value.put("horizontalBases", m.getRowNitrogenousBases());
        value.put("verticalBases", m.getColumnNitrogenousBases());
        value.put("sequence_1", names.get(0));
        value.put("sequence_2", names.get(1));

        return value;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @RequestMapping(value = "/post-file", method = RequestMethod.POST)
    public Map<String, String> handleFileUpload(@RequestParam("archivoFasta") MultipartFile file, RedirectAttributes redirectAttributes) {

        /*storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");*/
        Map<String, String> map = new HashMap<>();
        map.put("message", "successful");

        return map;
    }

}