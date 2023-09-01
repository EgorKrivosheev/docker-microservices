package by.grodno.krivosheev.transformation.controllers;

import by.grodno.krivosheev.transformation.services.BatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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

    @MockBean
    private BatchService batchService;

    @Test
    @WithMockUser(username = USERNAME_AUTH, password = PASSWORD_AUTH, roles = ROLES_AUTH)
    void uploadBatch_isOk() throws Exception {
        when(batchService.save(any()))
                .thenReturn(new File("0.zip"));

        mockMvc.perform(multipart(BATCHES_URL)
                        .file(UPLOAD_FILE_PARAM, new byte[1]))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = USERNAME_AUTH, password = PASSWORD_AUTH, roles = ROLES_AUTH)
    void uploadBatch_isBadRequest() throws Exception {
        mockMvc.perform(multipart(BATCHES_URL)
                        .file(UPLOAD_FILE_PARAM, new byte[0]))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON));
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
                .andExpect(content().contentType(APPLICATION_JSON));
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
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = USERNAME_AUTH, password = PASSWORD_AUTH, roles = ROLES_AUTH)
    void getItemsBatch_isBadRequest() throws Exception {
        mockMvc.perform(get(BATCHES_URL + "/text/items"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    void getItemsBatch_isUnauthorized() throws Exception {
        mockMvc.perform(get(BATCHES_URL + ITEMS_BATCH_URL))
                .andExpect(status().isUnauthorized());
    }
}
