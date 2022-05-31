import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';


const HomeScreen = ({navigation}) => {

  const navigateToDelivery = (deliveryId) => {
    navigation.navigate("Delivery", { deliveryId: deliveryId })
  }

  return(
    <View style={styles.container}>
      <Text>Home Screen</Text>
      <Button title="Delivery 10" onPress={() => navigateToDelivery(10)} />
      <Button title="Delivery 11" onPress={() => navigateToDelivery(11)} />
    </View>
  )
}

const AcceptedListScreen = ({navigation}) => {

  const navigateToDelivery = (deliveryId) => {
    navigation.navigate("Delivery", { deliveryId: deliveryId })
  }

  return(
    <View style={styles.container}>
      <Text>Accepted Deliveries</Text>
      <Button title="Delivery 10" onPress={() => navigateToDelivery(10)} />
      <Button title="Delivery 11" onPress={() => navigateToDelivery(11)} />
    </View>
  )
}

const DeliveryDetails = ({ route, navigation}) => {
  const { deliveryId } = route.params;

  return(
    <View>
      <Text>Delivery {deliveryId}</Text>
    </View>
  )
}


const Tab = createBottomTabNavigator();

const TabNavigationView = () => {
  return(
    <Tab.Navigator initialRouteName='Home'>
      <Tab.Screen name="Home" component={HomeScreen} />
      <Tab.Screen name="Accepted Deliveries" component={AcceptedListScreen} />
    </Tab.Navigator>
  )
}


const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName='Menus'>
        <Stack.Screen name='Menus' component={TabNavigationView} />
        <Stack.Screen name="Delivery" component={DeliveryDetails} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

// <View style={styles.container}>
//   <Text>Open up App.js to start working on your app!</Text>
//   <StatusBar style="auto" />
// </View>


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
