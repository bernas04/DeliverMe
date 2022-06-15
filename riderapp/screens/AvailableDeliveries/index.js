import { StyleSheet, Text, View, Button } from 'react-native';
import DeliveryList from '../../components/DeliveryList';
import React, { useEffect, useState } from "react";


const TEST_DATA = [
  {
    id: 10,
    name: "a cool delivery"
  },
  {
    id: 11,
    name: "a lame delivery"
  }
];

const AvailableDeliveriesScreen = ({navigation}) => {

    const [fetchError, setFetchError] = useState(false);
    const [deliveries, setDeliveries] = useState([]);
    
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
            console.log(data)
        })
        .catch((reason) => {
            console.log(reason)
            setFetchError(true)
        })
    }, [])
    
    console.log("--> " ,deliveries)
    return(
      <View style={styles.container}>
        <Text style={styles.title}>Available Deliveries</Text>
        
        <View style={styles.list}>
          <DeliveryList items={deliveries} />
        </View>
        
      </View>
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