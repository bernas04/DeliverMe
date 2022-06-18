import { StatusBar } from 'expo-status-bar';
import { StyleSheet } from 'react-native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import AvailableDeliveriesScreen from './screens/AvailableDeliveries';
import AcceptedDeliveriesScreen from './screens/AcceptedDeliveries';
import DeliveryDetailsScreen from './screens/DeliveryDetails';
import LoginScreen from './screens/Login';



const Tab = createBottomTabNavigator();

const TabNavigationView = () => {
  return(
    <Tab.Navigator initialRouteName='Available' screenOptions={{ headerShown: false }}  >
      <Tab.Screen name="Available" component={AvailableDeliveriesScreen} />
      <Tab.Screen name="Accepted" component={AcceptedDeliveriesScreen} />
      <Tab.Screen name="Profile" component={LoginScreen} />
    </Tab.Navigator>
  )
}


const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName='Menus' screenOptions={{ headerShown: false }} >
        <Stack.Screen name='Menus' component={TabNavigationView} />
        <Stack.Screen
          name="Delivery"
          component={DeliveryDetailsScreen}
          options={({route}) => ({ headerShown: true, title: `Delivery #${route.params.deliveryId}` })}
        />
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
