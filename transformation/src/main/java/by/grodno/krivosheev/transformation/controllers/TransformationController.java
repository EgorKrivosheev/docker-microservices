package by.grodno.krivosheev.transformation.controllers;

import by.grodno.krivosheev.transformation.dto.BatchInfoDTO;

import by.grodno.krivosheev.transformation.mappers.BatchMapper;
import by.grodno.krivosheev.transformation.mappers.ItemMapper;

import by.grodno.krivosheev.transformation.response.AbstractResponse;
import by.grodno.krivosheev.transformation.response.ListBatchResponse;
import by.grodno.krivosheev.transformation.response.ListUnifiedResponse;

import by.grodno.krivosheev.transformation.services.BatchService;
import by.grodno.krivosheev.transformation.services.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springdoc.api.annotations.ParameterObject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.Collections;
import java.util.List;

@Tag(name = "Transformation", description = "The rest service which consumes the data and just transforms it to a unified format")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/batches")
public class TransformationController {
    private final BatchService batchService;
    private final ItemService itemService;
    private final BatchMapper batchMapper;
    private final ItemMapper itemMapper;

    @Operation(summary = "Ingest endpoint")
    @PostMapping(produces = "application/octet-stream")
    public ResponseEntity<AbstractResponse> uploadBatch(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Don't upload file because he is empty!");
        }

        File uploadFile = batchService.save(file);
        long id = Long.parseLong(uploadFile.getName().substring(0, uploadFile.getName().length() - 4));
        List<BatchInfoDTO> list = Collections.singletonList(batchMapper.batchEntityToBatchInfoDTO(batchService.getBatch(id)));
        return responseList(new ListBatchResponse(HttpStatus.OK, list), new HttpHeaders());
    }

    @Operation(summary = "List of all uploads (ordered by date)")
    @GetMapping(produces = "application/json")
    public ResponseEntity<ListBatchResponse> getBatches(@ParameterObject @PageableDefault(size = 25, sort = "uploadDate",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return responseList(new ListBatchResponse(HttpStatus.OK, batchMapper.listBatchEntityToListBatchInfoDTO(batchService.getAll(pageable))));
    }

    @Operation(summary = "Batch content with pagination (in the order from original file)")
    @GetMapping(value = "/{batchId}/items", produces = "application/json")
    public ResponseEntity<ListUnifiedResponse> getItemsBatch(@PathVariable Long batchId,
                                                             @ParameterObject @PageableDefault(size = 25) Pageable pageable) {
        return responseList(new ListUnifiedResponse(HttpStatus.OK, itemMapper.listItemEntityToListUnifiedFormatDTO(itemService.findAllByIdBatch(batchId, pageable))));
    }

    private <T> ResponseEntity<T> responseList(T response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<AbstractResponse> responseList(AbstractResponse response, HttpHeaders headers) {
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
