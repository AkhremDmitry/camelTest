package com.epam.brest.course.dao;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public abstract class AbstractCall {

        /**
         * Property id.
         */
        protected int callId;

        /**
         * Property description.
         */
        protected String description;

        /**
         * Property address.
         */
        @Size(min = 3, message = "Address cannot contain less "
                + "than 3 characters.")
        @Size(max = 255, message = "Address cannot contain more "
                + "than 255 characters.")
        protected String address;

        /**
         * Property crewId.
         */
        @Positive
        protected int crewId;

        /**
         * Get call id.
         * @return CallId.
         */
        public final int getCallId() {
            return callId;
        }

        /**
         * Set call id.
         * @param callId CallId.
         */
        public final void setCallId(final int callId) {
            this.callId = callId;
        }

        /**
         * Get description.
         * @return Some description.
         */
        public final String getDescription() {
            return description;
        }

        /**
         * Set description.
         * @param description Some description.
         */
        public final void setDescription(final String description) {
            this.description = description;
        }

        /**
         * Get address.
         * @return Address.
         */
        public final String getAddress() {
            return address;
        }

        /**
         * Set address.
         * @param address address.
         */
        public final void setAddress(final String address) {
            this.address = address;
        }

        /**
         * Get crew id.
         * @return CrewId.
         */
        public final int getCrewId() {
            return crewId;
        }

        /**
         * Set crew id.
         * @param crewId CrewId.
         */
        public final void setCrewId(final int crewId) {
            this.crewId = crewId;
        }

}
