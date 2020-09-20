db.getCollection("transaction").find(
    {
        $and: [
            {
                accountType: 
                {
                    $in: [1,2]
                    
                }
            }, 
            {
                customerId: 
                {
                    $in: [1]
                    
                }
                
            }
        ]
    }
)