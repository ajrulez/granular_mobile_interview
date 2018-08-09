//
//  Networking.h
//  CodingAssignmet
//
//  Created by Granular Inc. on 7/20/18.
//  Copyright © 2018 Granular Inc.. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void (^CompletionBlock)(NSArray<NSDictionary<NSString*, NSString*>*>*, NSError*);

@interface Networking : NSObject

+ (void)requestDataWithURL:(NSURL*)url completion:(CompletionBlock)block;

@end
