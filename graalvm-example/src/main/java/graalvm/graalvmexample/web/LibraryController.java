package graalvm.graalvmexample.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/libraries")
@RequiredArgsConstructor
public class LibraryController {
    // for jpa test

    private final LibraryRepository libraryRepository;

    @GetMapping
    public ResponseEntity<List<Library>> getLibraries() {
        return ResponseEntity.ok(libraryRepository.findAll());
    }

}
