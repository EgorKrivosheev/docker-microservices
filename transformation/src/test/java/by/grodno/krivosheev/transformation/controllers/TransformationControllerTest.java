package by.grodno.krivosheev.transformation.controllers;

import by.grodno.krivosheev.transformation.mappers.BatchMapper;
import by.grodno.krivosheev.transformation.mappers.ItemMapper;

import by.grodno.krivosheev.transformation.services.BatchService;
import by.grodno.krivosheev.transformation.services.ItemService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransformationController.class)
class TransformationControllerTest {
    private static final String USERNAME_AUTH = "admin";
    private static final String PASSWORD_AUTH = "admin";
    private static final String ROLES_AUTH = "ADMIN";

    private static final String BATCHES_URL = "/batches";
    private static final String ITEMS_BATCH_URL = "/0/items";
    private static final String UPLOAD_FILE_PARAM = "file";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BatchService batchService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private BatchMapper batchMapper;

    @MockBean
    private ItemMapper itemMapper;

    @Test
    @WithMockUser(username = USERNAME_AUTH, password = PASSWORD_AUTH, roles = ROLES_AUTH)
    void uploadBatch_isOk() throws Exception {
        when(batchService.save(any(MultipartFile.class))).thenReturn(new File("0.zip"));
        mockMvc.perform(multipart(BATCHES_URL)
                        .file(UPLOAD_FILE_PARAM, new byte[1]))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = USERNAME_AUTH, password = PASSWORD_AUTH, roles = ROLES_AUTH)
    void uploadBatch_isBadRequest() throws Exception {
        mockMvc.perform(multipart(BATCHES_URL)
                        .file(UPLOAD_FILE_PARAM, new byte[0]))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void uploadBatch_isUnauthorized() throws Exception {
        mockMvc.perform(multipart(BATCHES_URL)
                        .file(UPLOAD_FILE_PARAM, new byte[0]))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME_AUTH, password = PASSWORD_AUTH, roles = ROLES_AUTH)
    void getBatches_isOk() throws Exception {
        mockMvc.perform(get(BATCHES_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getBatches_isUnauthorized() throws Exception {
        mockMvc.perform(get(BATCHES_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME_AUTH, password = PASSWORD_AUTH, roles = ROLES_AUTH)
    void getItemsBatch_isOk() throws Exception {
        mockMvc.perform(get(BATCHES_URL + ITEMS_BATCH_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = USERNAME_AUTH, password = PASSWORD_AUTH, roles = ROLES_AUTH)
    void getItemsBatch_isBadRequest() throws Exception {
        mockMvc.perform(get(BATCHES_URL + "/text/items"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getItemsBatch_isUnauthorized() throws Exception {
        mockMvc.perform(get(BATCHES_URL + ITEMS_BATCH_URL))
                .andExpect(status().isUnauthorized());
    }
}