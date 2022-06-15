import { useState, useEffect } from 'react';
import { StyleSheet, View, Text, Button, Alert } from 'react-native';

const DeliveryDetailsScreen = ({ route, navigation }) => {
    const { deliveryId } = route.params;


    // temporary fix for development, remove in production
    const process = {env: {REACT_APP_API_URL: "http://localhost:8080/api"}}

    const [item, setItem] = useState(null);
    const [riderID, setPostId] = useState(1);
    const [fetchError, setFetchError] = useState(false)
    const [processing, setProcessing] = useState(false)


    const fetchDeliveryData = () => {
      fetch(`${process.env.REACT_APP_API_URL}/purchases/Purchase?id=${deliveryId}`)
      .then(response => response.json())
      .then(data => {
        setItem(data)
        setProcessing(false)
      })
      .catch((reason)=>{
        console.log(reason)
        setFetchError(true)
      })
    }

    useEffect(()=>{
      fetchDeliveryData()
    },[])


    const acceptOrder = () => {
      setProcessing(true)

      const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' }
      };

      fetch(`${process.env.REACT_APP_API_URL}/purchases/confirmPurchase/${deliveryId}?riderId=${riderID}`, requestOptions)
      .then(response => response.json())
      .then(data => {
        fetchDeliveryData()
      })
      .catch((reason) => {
        console.log(reason)
        setProcessing(false)
        Alert.alert(
          "Operation failed"
        )
      })
    }
  
    return(
      fetchError ?
      <View style={styles.container}>
        <Text>An error ocurred while fetching data</Text>
      </View>
      :
      item == null ?
      <View style={styles.container}>
        <Text>Loading...</Text>
      </View>
      :
      <View style={styles.container}>
        <Text style={styles.title}>Store: <strong>{item.store.name}</strong></Text>
        <Text style={styles.title}>Client: <strong>{item.client}</strong></Text>

        <Text>
          From:<br/>
          {item.store.address.road}, {item.store.address.city}<br/>
          To:<br/>
          {item.address.road}, {item.address.city}
        </Text>

        <View style={styles.separator}></View>

        {
          item.rider == null ?
          <Button style={styles.button} title='Accept' onPress={acceptOrder} disabled={processing} />
          :
          <Button style={styles.button} title='Complete Delivery' onPress={()=>{}} disabled={processing} />
        }
      </View>
    )
};

const styles = StyleSheet.create({
    title: {
      fontSize: 20
    },
    container: {
      padding: 16
    },
    separator: {
      marginVertical: 4
    }
});

export default DeliveryDetailsScreen;