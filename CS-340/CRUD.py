from pymongo import MongoClient
from bson.objectid import ObjectId
import urllib.parse

class AnimalShelter(object):
    """ CRUD operations for Animal collection in MongoDB """
    def __init__(self, _password, _username = 'aacuser'):
        # Initializing the MongoClient. This helps to 
        # access the MongoDB databases and collections.
        # This is hard-wired to use the aac database, the 
        # animals collection, and the aac user.
        # Definitions of the connection string variables are
        # unique to the individual Apporto environment.
        #
        USER = urllib.parse.quote_plus(_username)
        PASS = urllib.parse.quote_plus(_password)
        HOST = 'nv-desktop-services.apporto.com'
        PORT = 33712
        DB = 'AAC'
        COL = 'animals'
        #
        # Initialize Connection
        #
        self.client = MongoClient('mongodb://%s:%s@%s:%d' % (USER,PASS,HOST,PORT))
        self.database = self.client['%s' % (DB)]
        self.collection = self.database['%s' % (COL)]

# Complete this create method to implement the C in CRUD.
    def create(self, data):
        if data is not None:
            insertSuccess = self.database.animals.insert_one(data) # data should be a dictionary
            # Check insertSucess for operation
            if insertSuccess == 0:
                return False
            # Default return will be true
            return True
        else:
            raise Exception("Nothing to save, because data parameter is empty")

# Create method to implement the R in CRUD
    def read(self, searchData):
        if searchData:
            _data = self.database.animals.find(searchData, {'_id' : 0})
                                 
        else:
            _data = self.database.animals.find({},{'_id' : 0})
                                  
        return _data
    
# Create method to implement the U in CRUD
    def update(self, searchData, updateData, multiple=False):
        if searchData and updateData:
            # User wants to update multiple entries
            if multiple:
                result = self.collection.update_many(searchData, {"$set": updateData})
            # User wants to update one entry
            else:
                result = self.collection.update_one(searchData, {"$set": updateData})

        # Return number of documents modified and total number of documents matched
        return {
            'matched_count': result.matched_count,
            'modified_count': result.modified_count
        }


# Create method to implement the D in CRUD
    def delete(self, deleteData, multiple=False):
        # If user wants to delete multiple entries
        if multiple:
            result = self.collection.delete_many(deleteData)
        # If user wants to delete one entry
        else:
            result = self.collection.delete_one(deleteData)
    
        # Return the number of documents deleted
        return result.deleted_count