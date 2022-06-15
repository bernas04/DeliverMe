import { StyleSheet, Text, View, Button } from 'react-native';
import DeliveryList from '../../components/DeliveryList';
import React, { useEffect, useState } from "react";


const AvailableDeliveriesScreen = ({navigation}) => {

    const [fetchError, setFetchError] = useState(false);
    const [deliveries, setDeliveries] = useState([]);
    const [hasDeliveries, setNoDeliveries] = useState(false);
    
    // change to requested and in-progress only
    useEffect(()=>{
        fetch(
            `http://localhost:8080/api/purchases/requestedPurchase`,
            {
                headers: {'Access-Control-Allow-Origin': '*'}
            }
        )
        .then(response => response.json())
        .then(data => {
            setDeliveries(data)
            setNoDeliveries(data.length)
            if (hasDeliveries!==0){
              setNoDeliveries(true);
            }
        })
        .catch((reason) => {
            console.log(reason)
            setFetchError(true)
        })
    }, [])
    
    
    return(
      hasDeliveries?
      <>
      <View style={styles.container}>
        <Text style={styles.title}>Available Deliveries</Text>
        
        <View style={styles.list}>
          <DeliveryList items={deliveries} />
        </View>
        
      </View>
      </>
      :
      <>
        <View style={styles.container}>
          <Text style={styles.title}>No Available Deliveries</Text>
        </View>
      </>
    )
};

const styles = StyleSheet.create({
    title: {
      fontSize: 20
    },
    container: {
        flex: 1,
        padding: 16,
        paddingTop: 48,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
    list: {
      flex: 1,
      width: "80%"
    }
});

export default AvailableDeliveriesScreen;