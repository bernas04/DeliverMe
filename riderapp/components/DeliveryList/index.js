import { useNavigation } from '@react-navigation/native';
import { StyleSheet, FlatList, Text, TouchableOpacity } from 'react-native';

const DeliveryList = ({ items }) => {

    const navigation = useNavigation();
    const renderItem = ({item}) => {
        const pressHandler = () =>

            navigation.navigate("Delivery", { deliveryId: item.id });

        return(
            <TouchableOpacity onPress={pressHandler} style={styles.item} >
                <Text style={{color: "#fff", fontSize: 20}}><strong>{item.client}</strong></Text>
                <br></br>
                <Text style={{color: "#fff"}}>From {item.store.address.road}, {item.store.address.city} to {item.address.road}, {item.address.city}</Text>
            </TouchableOpacity>
        )
    };

    return(
        <FlatList
            data={items}
            renderItem={renderItem}
            keyExtractor={item=>item.id}
            style={styles.list}
        />
    )

}

const styles = StyleSheet.create({
    list: {
        //width: "100%"
        flex: 1
    },
    item: {
        backgroundColor: "#4169e1",
        color: "#ffffff",
        textAlign: "center",
        marginVertical: 4,
        padding: 16,
        borderRadius: 6
    }
});

export default DeliveryList;