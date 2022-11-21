package com.Webdrama.Rodanthe.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    private final AmazonS3 amazonS3;

    public String getCoverImgPath(Long workId){
        String path = amazonS3.getUrl(bucketName, "UserImg/"+workId+"/"+workId+".png").toString();
        return path;
    }

    public String getVideoPath(Long workId, Long episode){
        String path = amazonS3.getUrl(bucketName,"DramaVideo/"+workId+"/"+episode+".mp4").toString();
        return path;
    }
}
