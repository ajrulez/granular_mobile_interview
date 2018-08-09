//
//  Networking.m
//  CodingAssignmet
//
//  Created by Granular Inc. on 7/20/18.
//  Copyright © 2018 Granular Inc.. All rights reserved.
//

#import "Networking.h"


@implementation Networking

+ (void)requestDataWithURL:(NSURL*)url completion:(CompletionBlock)block {
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    
    NSURLSession *session = [NSURLSession sharedSession];
    NSURLSessionDataTask *task = [session dataTaskWithRequest:request
                                            completionHandler:
                                  ^(NSData *data, NSURLResponse *response, NSError *error) {
                                      NSLog(@"RESPONSE: %@",response);
                                      NSLog(@"DATA: %@",data);
                                      
                                      if (!error) {
                                          // Success
                                          if ([response isKindOfClass:[NSHTTPURLResponse class]]) {
                                              NSError *jsonError;
                                              NSArray *jsonResponse = [NSJSONSerialization JSONObjectWithData:data options:0 error:&jsonError];
                                              
                                              if (jsonError) {
                                                  // Error Parsing JSON
                                                  block(nil, error);
                                              } else {
                                                  // Success Parsing JSON
                                                  // Log NSDictionary response:
                                                  NSLog(@"%@",jsonResponse);
                                                  block(jsonResponse, nil);
                                              }
                                          }  else {
                                              //Web server is returning an error
                                              block(nil, error);
                                          }
                                      } else {
                                          // Fail
                                          block(nil, error);
                                          NSLog(@"error : %@", error.description);
                                      }
    }];
    
    [task resume];
}

@end
