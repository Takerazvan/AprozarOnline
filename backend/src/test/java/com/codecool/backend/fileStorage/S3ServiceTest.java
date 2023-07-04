//package com.codecool.backend.fileStorage;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import software.amazon.awssdk.core.ResponseInputStream;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.GetObjectRequest;
//import software.amazon.awssdk.services.s3.model.GetObjectResponse;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//
//import java.io.IOException;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class S3ServiceTest {
//
//    @Mock
//    private S3Client s3Client;
//
//    private S3Service uut;
//
//    @BeforeEach
//    void setUp(){
//        uut=new S3Service(s3Client);
//    }
//
//    @Test
//    void canPutObject() throws IOException{
//        String bucket="product";
//        String key="foo";
//        byte[] data="Hello World".getBytes();
//
//        uut.putObject(bucket,key,data);
//
//        ArgumentCaptor<PutObjectRequest> putObjectRequestArgumentCaptor=
//                ArgumentCaptor.forClass(PutObjectRequest.class);
//
//        ArgumentCaptor<RequestBody> requestBodyArgumentCaptor =
//                ArgumentCaptor.forClass(RequestBody.class);
//
//        verify(s3Client).putObject(
//                putObjectRequestArgumentCaptor.capture(),
//                requestBodyArgumentCaptor.capture()
//        );
//
//        PutObjectRequest putObjectRequestArgumentCaptorValue =
//                putObjectRequestArgumentCaptor.getValue();
//
//        assertThat(putObjectRequestArgumentCaptorValue.bucket()).isEqualTo(bucket);
//        assertThat(putObjectRequestArgumentCaptorValue.key()).isEqualTo(key);
//
//        RequestBody requestBodyArgumentCaptorValue =
//                requestBodyArgumentCaptor.getValue();
//
//        assertThat(
//                requestBodyArgumentCaptorValue.contentStreamProvider().newStream().readAllBytes()
//        ).isEqualTo(
//                RequestBody.fromBytes(data).contentStreamProvider().newStream().readAllBytes()
//        );
//    }
//
//    @Test
//    void canGetObject() throws IOException {
//        // Given
//        String bucket = "product";
//        String key = "foo";
//        byte[] data = "Hello World".getBytes();
//
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket(bucket)
//                .key(key)
//                .build();
//
//        ResponseInputStream<GetObjectResponse> res = mock(ResponseInputStream.class);
//        when(res.readAllBytes()).thenReturn(data);
//
//        when(s3Client.getObject(eq(getObjectRequest))).thenReturn(res);
//
//        // When
//        byte[] bytes = uut.getObject(bucket, key);
//
//        // Then
//        assertThat(bytes).isEqualTo(data);
//    }
//
//    @Test
//    void willThrowWhenGetObject() throws IOException {
//        // Given
//        String bucket = "product";
//        String key = "foo";
//        byte[] data = "Hello World".getBytes();
//
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket(bucket)
//                .key(key)
//                .build();
//
//        ResponseInputStream<GetObjectResponse> res = mock(ResponseInputStream.class);
//        when(res.readAllBytes()).thenThrow(new IOException("Cannot read bytes"));
//
//        when(s3Client.getObject(eq(getObjectRequest))).thenReturn(res);
//
//        // When
//        // Then
//        assertThatThrownBy(() -> uut.getObject(bucket, key))
//                .isInstanceOf(RuntimeException.class)
//                .hasMessageContaining("Cannot read bytes")
//                .hasRootCauseInstanceOf(IOException.class);
//
//    }
//}
//
