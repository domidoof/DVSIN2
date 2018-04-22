package com.example.adrian.dvsin.Container;

// Pakete für Parcelable 

import android.os.Parcel;
import android.os.Parcelable;


// Klassendeklaration

public class ContainerArt implements Parcelable {

    // Instanzvariable

	public int containerart;



    // Konstruktor

  public ContainerArt(int containerart) {

  	this.containerart = containerart;

  }

	// METHODEN

    // Get-Methoden


		public int getContainerart() {
		return containerart;
	}


    // Set-Methoden

		public void setContainerart(int containerart){
				this.containerart = containerart;
			}

	// Parcelable-Teil

	 @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

		dest.writeInt(this.containerart);
    }

    protected ContainerArt(Parcel in) {

		this.containerart = in.readInt();
    }

    public static final Creator<ContainerArt> CREATOR = new Creator<ContainerArt>() {
		
        @Override
        public ContainerArt createFromParcel(Parcel source) {
            return new ContainerArt(source);
        }

        @Override
        public ContainerArt[] newArray(int size) {
            return new ContainerArt[size];
        }
    };
}



