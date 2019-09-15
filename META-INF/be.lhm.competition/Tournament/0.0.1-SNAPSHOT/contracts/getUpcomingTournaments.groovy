package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.HttpStatus

Contract.make {
    priority 1
    name 'get the upcoming tournaments'
    description '''
Given:
When:
Then:
'''
    request {
        method GET()
        urlPath($(client('/api/v1/tournaments'), server('/tournaments'))) {
            queryParameters {
                parameter 'limit', 2
            }
        }
        headers {
            contentType applicationJson()
            header authorization(), anyNonEmptyString()
        }
    }
    response {
        status OK()
        body([
                [
                        name: $(client('Golden Dragon Cup 2019'), server(anyNonEmptyString())),
                        date: $(client('2014-08-09'), producer(regex(isoDate())))
                ],
                [
                        name: $(client('Tigers Cup: Invitational'), server(anyNonEmptyString())),
                        date: $(client('2019-04-04'), server(regex(isoDate())))
                ]
        ])
    }
}
